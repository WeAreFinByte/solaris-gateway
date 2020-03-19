package com.finbyte.oauth.resource.controller;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
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

  @RequestMapping(value = "/env/v1/persons", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String testPersons() {
    return "[\n" + "{\n"
        + "  \"salutation\": \"MR\",\n"
        + "  \"title\": \"DR\",\n"
        + "  \"first_name\": \"Peter\",\n"
        + "  \"last_name\": \"Mustermann\",\n"
        + "  \"address\": {\n"
        + "    \"line_1\": \"Musterstrasse 1\",\n"
        + "    \"line_2\": \"Etage 2\",\n"
        + "    \"postal_code\": \"10409\",\n"
        + "    \"city\": \"Berlin\",\n"
        + "    \"country\": \"DE\",\n"
        + "    \"state\": \"BE\"\n"
        + "  },\n"
        + "  \"contact_address\": {\n"
        + "    \"line_1\": \"Musterstrasse 1\",\n"
        + "    \"line_2\": \"Etage 2\",\n"
        + "    \"postal_code\": \"10409\",\n"
        + "    \"city\": \"Berlin\",\n"
        + "    \"country\": \"DE\",\n"
        + "    \"state\": \"BE\"\n"
        + "  },\n"
        + "  \"email\": \"person@example.com\",\n"
        + "  \"mobile_number\": \"+49301234567\",\n"
        + "  \"birth_name\": \"Doe\",\n"
        + "  \"birth_date\": \"1972-12-24\",\n"
        + "  \"birth_city\": \"Berlin\",\n"
        + "  \"birth_country\": \"DE\",\n"
        + "  \"nationality\": \"DE\",\n"
        + "  \"employment_status\": \"EMPLOYED\",\n"
        + "  \"job_title\": \"Head of everything\",\n"
        + "  \"tax_information\": {\n"
        + "    \"tax_assessment\": \"SEPARATE\",\n"
        + "    \"marital_status\": \"MARRIED\"\n"
        + "  },\n"
        + "  \"fatca_relevant\": true,\n"
        + "  \"fatca_crs_confirmed_at\": \"2017-01-01T00:00:00Z\",\n"
        + "  \"business_purpose\": \"Online language training services\",\n"
        + "  \"industry\": \"OTHER_SERVICES\",\n"
        + "  \"industry_key\": \"EDUCATION\",\n"
        + "  \"terms_conditions_signed_at\": \"2017-01-01T00:00:00Z\",\n"
        + "  \"own_economic_interest_signed_at\": \"2017-01-01T00:00:00Z\"\n"
        + "}" + "]";
  }

  @RequestMapping(value = "/env/v1/persons/{id}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String testPerson(@PathVariable String id) {
    return "{\n"
        + "  \"id\": \""+id+ "\",\n"
        + "  \"salutation\": \"MR\",\n"
        + "  \"title\": \"DR\",\n"
        + "  \"first_name\": \"Peter\",\n"
        + "  \"last_name\": \"Mustermann\",\n"
        + "  \"address\": {\n"
        + "    \"line_1\": \"Musterstrasse\",\n"
        + "    \"line_2\": \"Musterstrasse\",\n"
        + "    \"postal_code\": \"10409\",\n"
        + "    \"city\": \"Berlin\",\n"
        + "    \"country\": \"DE\",\n"
        + "    \"state\": \"BE\"\n"
        + "  },\n"
        + "  \"contact_address\": {\n"
        + "    \"line_1\": \"Musterstrasse\",\n"
        + "    \"line_2\": \"Musterstrasse\",\n"
        + "    \"postal_code\": \"10409\",\n"
        + "    \"city\": \"Berlin\",\n"
        + "    \"country\": \"DE\",\n"
        + "    \"state\": \"BE\"\n"
        + "  },\n"
        + "  \"email\": \"person@example.com\",\n"
        + "  \"mobile_number\": \"+49301234567\",\n"
        + "  \"birth_name\": \"Doe\",\n"
        + "  \"birth_date\": \"1972-12-24\",\n"
        + "  \"birth_city\": \"Berlin\",\n"
        + "  \"birth_country\": \"DE\",\n"
        + "  \"nationality\": \"DE\",\n"
        + "  \"employment_status\": \"EMPLOYED\",\n"
        + "  \"job_title\": \"Head of everything\",\n"
        + "  \"tax_information\": {\n"
        + "    \"tax_assessment\": \"SEPARATE\",\n"
        + "    \"marital_status\": \"MARRIED\"\n"
        + "  },\n"
        + "  \"fatca_relevant\": true,\n"
        + "  \"fatca_crs_confirmed_at\": \"2017-01-01T00:00:00.000Z\",\n"
        + "  \"business_purpose\": \"Online language training services\",\n"
        + "  \"industry\": \"OTHER_SERVICES\",\n"
        + "  \"industry_key\": \"EDUCATION\",\n"
        + "  \"terms_conditions_signed_at\": \"2017-01-01T00:00:00.000Z\",\n"
        + "  \"flagged_by_compliance\": false,\n"
        + "  \"own_economic_interest_signed_at\": \"2017-01-01T00:00:00Z\"\n"
        + "}";
  }


  @RequestMapping(value = "/env/v1/accounts", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String testAccounts() {
    String randomId = Integer.toString((int)(Math.random() * 100) + 1);
    String randomBalance = Double.toString((Math.random() * 100) + 1);
    String randomAvailableBalance = Double.toString((Math.random() * 100) + 1);
    return "[\n"
        + "  {\n"
        + "    \"id\": \"" + randomId +"\",\n"
        + "    \"iban\": \"string\",\n"
        + "    \"bic\": \"string\",\n"
        + "    \"type\": \"string\",\n"
        + "    \"balance\": {\n"
        + "      \"value\": " + randomBalance +",\n"
        + "      \"unit\": \"string\",\n"
        + "      \"currency\": \"string\"\n"
        + "    },\n"
        + "    \"available_balance\": {\n"
        + "      \"value\": " + randomAvailableBalance +",\n"
        + "      \"unit\": \"string\",\n"
        + "      \"currency\": \"string\"\n"
        + "    },\n"
        + "    \"locking_status\": \"string\",\n"
        + "    \"locking_reasons\": \"string\",\n"
        + "    \"account_limit\": {\n"
        + "      \"value\": 0,\n"
        + "      \"unit\": \"string\",\n"
        + "      \"currency\": \"string\"\n"
        + "    },\n"
        + "    \"person_id\": \"string\",\n"
        + "    \"business_id\": \"string\"\n"
        + "  }\n"
        + "]";
  }

}
