package com.erp.signoff.service;

/**
 * 配套厂（工艺厂）收货自动签收 Service。
 * <p>等价于 Oracle 存储过程 P_PRODUCE_SIGN_DETAIL_AUTO_PS，业务逻辑待实现。</p>
 */
public interface AutoSignService {

    /**
     * 收货生效时触发自动签收。
     * <p>等价 PL/SQL：P_PRODUCE_SIGN_DETAIL_AUTO_PS(P_ORGID, P_PROC_NO)。</p>
     *
     * @param orgId  收货组织ID（sf_proc_rcm.org_id）
     * @param procNo 收货单号（sf_proc_rcm.proc_no）
     */
    void autoSign(Long orgId, String procNo);

    /**
     * 取消收货前校验：若该收货单已产生自动签收记录，则不允许取消。
     *
     * @param orgId  收货组织ID
     * @param procNo 收货单号
     */
    void assertCancelReceiptAllowed(Long orgId, String procNo);

    /**
     * 判断收货单是否已存在签收记录。
     *
     * @param orgId  收货组织ID
     * @param procNo 收货单号
     * @return true = 已签收（不允许取消收货）
     */
    boolean hasSignRecord(Long orgId, String procNo);
}
