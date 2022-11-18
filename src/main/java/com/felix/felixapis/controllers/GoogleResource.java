package com.felix.felixapis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleResource {
    private final Logger logger = LoggerFactory.getLogger(GoogleResource.class);

    @RequestMapping(value = "/.well-known/assetlinks.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProduct() {
        logger.debug("REST request to get assetlinks.json");
        return ResponseEntity.ok().body("[{\n" +
                "    \"relation\": [\"delegate_permission/common.handle_all_urls\"],\n" +
                "    \"target\": {\n" +
                "        \"namespace\": \"android_app\",\n" +
                "        \"package_name\": \"in.silive.felix\",\n" +
                "        \"sha256_cert_fingerprints\": [\n" +
                "            \"D5:4E:34:54:05:F9:DD:69:2A:00:1B:D1:C6:B7:F3:57:DB:26:06:AE:94:F1:20:06:D1:7A:EB:B4:C4:62:FE:8F\"\n" +
                "        ]\n" +
                "    }\n" +
                "}\n" +
                "]");
    }
}
