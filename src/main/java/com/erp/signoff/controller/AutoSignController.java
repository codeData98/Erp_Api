package com.erp.signoff.controller;

import com.erp.signoff.common.Result;
import com.erp.signoff.service.AutoSignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动签收测试接口（骨架）。
 */
@RestController
@RequestMapping("/api/auto-sign")
public class AutoSignController {

    private final AutoSignService autoSignService;

    public AutoSignController(AutoSignService autoSignService) {
        this.autoSignService = autoSignService;
    }

    /** 触发自动签收 */
    @PostMapping
    public Result<Void> autoSign(@RequestParam Long orgId, @RequestParam String procNo) {
        autoSignService.autoSign(orgId, procNo);
        return Result.success();
    }

    /** 取消收货前校验：已签收则返回业务错误 */
    @PostMapping("/assert-cancel")
    public Result<Void> assertCancelReceiptAllowed(@RequestParam Long orgId, @RequestParam String procNo) {
        autoSignService.assertCancelReceiptAllowed(orgId, procNo);
        return Result.success();
    }

    /** 查询收货单是否已签收 */
    @GetMapping("/has-sign")
    public Result<Boolean> hasSignRecord(@RequestParam Long orgId, @RequestParam String procNo) {
        return Result.success(autoSignService.hasSignRecord(orgId, procNo));
    }
}
