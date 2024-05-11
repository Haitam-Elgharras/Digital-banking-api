package ma.digital.digitalbanking.security;


import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder encoder = passwordEncoder();

        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(encoder.encode("12345"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .password(encoder.encode("12345"))
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(ar -> ar.requestMatchers("/auth/**").permitAll())
                .authorizeRequests(ar -> ar.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())// for testing we can send the username and password in Base64 in the authorization header
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    // jwt encoder where we can create a jwt token to send it to the client
    @Bean
    JwtEncoder jwtEncoder() {
//        String secretKey = "45yrkluajzd40arskbzzu9fyx7mwfdvjt9txqz8abcbyg7zyoevd5bwns5mx6xn5";
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));

    }

    // jwt decoder : when recieving a request from the client this beans will intercept the request and get the jwt token from the authorization header then decode it
   @Bean
    JwtDecoder jwtDecoder() {
//        String secretKey = "45yrkluajzd40arskbzzu9fyx7mwfdvjt9txqz8abcbyg7zyoevd5bwns5mx6xn5";
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }


    // to authenticate the user we need to the authentication manager
   @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) throws Exception{
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(daoAuthenticationProvider);
    }



}
