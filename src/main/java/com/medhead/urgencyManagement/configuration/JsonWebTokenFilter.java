package com.medhead.urgencyManagement.configuration;

import com.medhead.urgencyManagement.repository.AuthorizationRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class JsonWebTokenFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(JsonWebTokenFilter.class);
    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";

    @Autowired
    AuthorizationRepository authorizationRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Retrieve authorization header of the request.
        String requestTokenHeader = request.getHeader("AUTHORIZATION");

        if (requestTokenHeader == null || requestTokenHeader.isEmpty() || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get token
        String token = requestTokenHeader.split(" ")[1].trim();
        logger.info("Token is : " + token);

        if (!authorizationRepository.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = new User(this.getUsername(token), "", new ArrayList<>());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                List.of()
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    public String getUsername(String token) {
        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        JSONParser jsonParser = new JSONParser();

        String username = null;

        try {
            JSONObject jsonPayload = (JSONObject) jsonParser.parse(payload);

            username = (String) jsonPayload.get("sub");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }
}
