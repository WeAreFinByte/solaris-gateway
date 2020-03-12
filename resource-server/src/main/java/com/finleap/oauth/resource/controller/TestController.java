package com.finleap.oauth.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController()
@RequestMapping("uaa")
@PreAuthorize("#oauth2.hasAnyScope('uaa.resource')")
public class TestController {
  
  @ResponseBody
  @GetMapping("/header")
  public String testGetMethod(@RequestHeader Map<String, String> headers) {

    log.info("get header " + headers);
    return headers.toString();
  }

  @ResponseBody
  @PutMapping("/header")
  public String testPutMethod(@RequestHeader Map<String, String> headers) {

    log.info("put header " + headers);
    return headers.toString();
  }

  @ResponseBody
  @PostMapping("/header")
  public String testPostMethod(@RequestHeader Map<String, String> headers) {

    log.info("post header " + headers);
    return headers.toString();
  }

  @ResponseBody
  @PatchMapping("/header")
  public String testPatchMethod(@RequestHeader Map<String, String> headers) {

    log.info("patch header " + headers);
    return headers.toString();
  }

  @ResponseBody
  @DeleteMapping("/header")
  public String testDeleteMethod(@RequestHeader Map<String, String> headers) {

    log.info("delete header " + headers);
    return headers.toString();
  }

  @GetMapping("/user")
  @ResponseBody
  public String getOauth2Principal(OAuth2Authentication auth) {
    return "Access granted for " + auth.getPrincipal();
  }

}
