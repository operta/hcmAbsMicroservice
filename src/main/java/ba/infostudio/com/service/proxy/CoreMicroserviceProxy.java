package ba.infostudio.com.service.proxy;

import ba.infostudio.com.service.proxy.model.ApConstants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("hcmCoreMicroservice")
public interface CoreMicroserviceProxy {

    @GetMapping("/api/ap-constants/key/{name}")
    ResponseEntity<ApConstants> getApConstantByKey(@PathVariable(name = "name") String name,
                                                   @RequestHeader("Authorization") String token);
}
