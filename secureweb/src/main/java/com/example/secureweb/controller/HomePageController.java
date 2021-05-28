package com.example.secureweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homePage(Model model, @AuthenticationPrincipal OidcUser principal,
                           OAuth2AuthenticationToken authentication) {
        model.addAttribute("userInfo", principal.getUserInfo());
        model.addAttribute("details", authentication.getPrincipal().getAttributes());
        String idToken = principal.getIdToken().getTokenValue();
        model.addAttribute("idToken", idToken);
        model.addAttribute("logout", "https://dev-08075771.okta.com/oauth2/default/v1/logout?id_token_hint=" + idToken + "&post_logout_redirect_uri=http://localhost:8080");

        return "index.html";
    }

}
