package com.divudi.data;

import com.divudi.entity.Bill;
import com.divudi.entity.Department;
import com.divudi.entity.ReportTemplate;
import com.divudi.entity.WebUser;
import com.divudi.entity.channel.SessionInstance;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author buddhika
 */
public class ReportTemplateRowBundle implements Serializable {

    private static final long serialVersionUID = 1L;

    // UUID field to uniquely identify each object
    private UUID id;

    private List<ReportTemplateRowBundle> bundles;
    private ReportTemplate reportTemplate;
    private List<ReportTemplateRow> reportTemplateRows;

    private Double grossTotal;
    private Double discount;
    private Double total;

    private Double hospitalTotal;
    private Double staffTotal;
    private Double ccTotal;

    private Double totalIn;
    private Double totalOut;
    private Long countIn;
    private Long countOut;
    private Long count;
    private String name;
    private String bundleType;
    private String description;
    private SessionInstance sessionInstance;
    private Long long1;
    private Long long2;
    private Long long3;
    private Long long4;
    private Long long5;
    private Long long6;
    private Long long7;
    private Long long8;
    private Long long9;
    private Long long10;

    private double onCallValue;
    private double cashValue;
    private double cardValue;
    private double multiplePaymentMethodsValue;
    private double staffValue;
    private double creditValue;
    private double staffWelfareValue;
    private double voucherValue;
    private double iouValue;
    private double agentValue;
    private double chequeValue;
    private double slipValue;
    private double eWalletValue;
    private double patientDepositValue;
    private double patientPointsValue;
    private double onlineSettlementValue;

    // Booleans to track transactions
    private boolean hasOnCallTransaction;
    private boolean hasCashTransaction;
    private boolean hasCardTransaction;
    private boolean hasMultiplePaymentMethodsTransaction;
    private boolean hasStaffTransaction;
    private boolean hasCreditTransaction;
    private boolean hasStaffWelfareTransaction;
    private boolean hasVoucherTransaction;
    private boolean hasIouTransaction;
    private boolean hasAgentTransaction;
    private boolean hasChequeTransaction;
    private boolean hasSlipTransaction;
    private boolean hasEWalletTransaction;
    private boolean hasPatientDepositTransaction;
    private boolean hasPatientPointsTransaction;
    private boolean hasOnlineSettlementTransaction;

    private WebUser user;
    private Date date;
    private Department department;
    private Bill startBill;
    private Bill endBill;

    public ReportTemplateRowBundle() {
        this.id = UUID.randomUUID();
    }

    private double nullSafeDouble(Double value) {
        return value != null ? value : 0.0;
    }

    private void resetTotals() {
        grossTotal = 0.0;
        discount = 0.0;
        total = 0.0;
        hospitalTotal = 0.0;
        staffTotal = 0.0;
        ccTotal = 0.0;
        totalIn = 0.0;
        totalOut = 0.0;
        countIn = 0L;
        countOut = 0L;
        count = 0L;

        onCallValue = 0.0;
        cashValue = 0.0;
        cardValue = 0.0;
        multiplePaymentMethodsValue = 0.0;
        staffValue = 0.0;
        creditValue = 0.0;
        staffWelfareValue = 0.0;
        voucherValue = 0.0;
        iouValue = 0.0;
        agentValue = 0.0;
        chequeValue = 0.0;
        slipValue = 0.0;
        eWalletValue = 0.0;
        patientDepositValue = 0.0;
        patientPointsValue = 0.0;
        onlineSettlementValue = 0.0;

        hasOnCallTransaction = false;
        hasCashTransaction = false;
        hasCardTransaction = false;
        hasMultiplePaymentMethodsTransaction = false;
        hasStaffTransaction = false;
        hasCreditTransaction = false;
        hasStaffWelfareTransaction = false;
        hasVoucherTransaction = false;
        hasIouTransaction = false;
        hasAgentTransaction = false;
        hasChequeTransaction = false;
        hasSlipTransaction = false;
        hasEWalletTransaction = false;
        hasPatientDepositTransaction = false;
        hasPatientPointsTransaction = false;
        hasOnlineSettlementTransaction = false;
    }

    public void aggregateTotals() {
        resetTotals(); // Resets all totals before computation

        if (bundles != null) {
            for (ReportTemplateRowBundle childBundle : bundles) {
                System.out.println("childBundle = " + childBundle.getId().toString());
                grossTotal += nullSafeDouble(childBundle.grossTotal);
                discount += nullSafeDouble(childBundle.discount);
                total += nullSafeDouble(childBundle.total);
                hospitalTotal += nullSafeDouble(childBundle.hospitalTotal);
                staffTotal += nullSafeDouble(childBundle.staffTotal);
                ccTotal += nullSafeDouble(childBundle.ccTotal);
                totalIn += nullSafeDouble(childBundle.totalIn);
                totalOut += nullSafeDouble(childBundle.totalOut);

                // Increment counts
                countIn += childBundle.countIn != null ? childBundle.countIn : 0;
                countOut += childBundle.countOut != null ? childBundle.countOut : 0;
                count += childBundle.count != null ? childBundle.count : 0;

                // Payment values
                onCallValue += childBundle.onCallValue;
                cashValue += childBundle.cashValue;
                System.out.println("childBundle = " + childBundle.getCardValue());
                System.out.println("cashValue = " + cashValue);
                cardValue += childBundle.cardValue;
                multiplePaymentMethodsValue += childBundle.multiplePaymentMethodsValue;
                staffValue += childBundle.staffValue;
                creditValue += childBundle.creditValue;
                staffWelfareValue += childBundle.staffWelfareValue;
                voucherValue += childBundle.voucherValue;
                iouValue += childBundle.iouValue;
                agentValue += childBundle.agentValue;
                chequeValue += childBundle.chequeValue;
                slipValue += childBundle.slipValue;
                eWalletValue += childBundle.eWalletValue;
                patientDepositValue += childBundle.patientDepositValue;
                patientPointsValue += childBundle.patientPointsValue;
                onlineSettlementValue += childBundle.onlineSettlementValue;

                // Aggregate flags
                hasOnCallTransaction |= childBundle.hasOnCallTransaction;
                hasCashTransaction |= childBundle.hasCashTransaction;
                hasCardTransaction |= childBundle.hasCardTransaction;
                hasMultiplePaymentMethodsTransaction |= childBundle.hasMultiplePaymentMethodsTransaction;
                hasStaffTransaction |= childBundle.hasStaffTransaction;
                hasCreditTransaction |= childBundle.hasCreditTransaction;
                hasStaffWelfareTransaction |= childBundle.hasStaffWelfareTransaction;
                hasVoucherTransaction |= childBundle.hasVoucherTransaction;
                hasIouTransaction |= childBundle.hasIouTransaction;
                hasAgentTransaction |= childBundle.hasAgentTransaction;
                hasChequeTransaction |= childBundle.hasChequeTransaction;
                hasSlipTransaction |= childBundle.hasSlipTransaction;
                hasEWalletTransaction |= childBundle.hasEWalletTransaction;
                hasPatientDepositTransaction |= childBundle.hasPatientDepositTransaction;
                hasPatientPointsTransaction |= childBundle.hasPatientPointsTransaction;
                hasOnlineSettlementTransaction |= childBundle.hasOnlineSettlementTransaction;
            }
        }
    }

    public ReportTemplateRowBundle(List<ReportTemplateRow> reportTemplateRows) {
        this.reportTemplateRows = reportTemplateRows;
    }

    public UUID getId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportTemplateRowBundle that = (ReportTemplateRowBundle) o;
        return Objects.equals(getId(), that.id);
    }

    // Override hashCode() using UUID field
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    // Override toString() for better readability (optional)
    @Override
    public String toString() {
        return "ReportTemplateRowBundle{id=" + getId() + '}';
    }

    public void calculateTotals() {
        // Reset totals and boolean flags before starting calculation
        resetTotalsAndFlags();

        // Check if the list of rows is not null and not empty
        if (this.reportTemplateRows != null && !this.reportTemplateRows.isEmpty()) {
            // Aggregate values from each row and update transaction flags
            for (ReportTemplateRow row : this.reportTemplateRows) {
                addValueAndUpdateFlag("cash", safeDouble(row.getCashValue()));
                addValueAndUpdateFlag("card", safeDouble(row.getCardValue()));
                addValueAndUpdateFlag("multiplePaymentMethods", safeDouble(row.getMultiplePaymentMethodsValue()));
                addValueAndUpdateFlag("staff", safeDouble(row.getStaffValue()));
                addValueAndUpdateFlag("credit", safeDouble(row.getCreditValue()));
                addValueAndUpdateFlag("staffWelfare", safeDouble(row.getStaffWelfareValue()));
                addValueAndUpdateFlag("voucher", safeDouble(row.getVoucherValue()));
                addValueAndUpdateFlag("iou", safeDouble(row.getIouValue()));
                addValueAndUpdateFlag("agent", safeDouble(row.getAgentValue()));
                addValueAndUpdateFlag("cheque", safeDouble(row.getChequeValue()));
                addValueAndUpdateFlag("slip", safeDouble(row.getSlipValue()));
                addValueAndUpdateFlag("eWallet", safeDouble(row.getEwalletValue()));
                addValueAndUpdateFlag("patientDeposit", safeDouble(row.getPatientDepositValue()));
                addValueAndUpdateFlag("patientPoints", safeDouble(row.getPatientPointsValue()));
                addValueAndUpdateFlag("onlineSettlement", safeDouble(row.getOnlineSettlementValue()));
                addValueAndUpdateFlag("grossTotal", safeDouble(row.getGrossTotal()));
                addValueAndUpdateFlag("discount", safeDouble(row.getDiscount()));
                addValueAndUpdateFlag("total", safeDouble(row.getTotal()));
                addValueAndUpdateFlag("hospitalTotal", safeDouble(row.getHospitalTotal()));
                addValueAndUpdateFlag("staffTotal", safeDouble(row.getStaffTotal()));
                addValueAndUpdateFlag("ccTotal", safeDouble(row.getCcTotal()));
            }
        }
    }

    public void calculateTotalsByPayments() {
        resetTotalsAndFlags();

        if (this.reportTemplateRows != null && !this.reportTemplateRows.isEmpty()) {
            for (ReportTemplateRow row : this.reportTemplateRows) {
                if (row.getPayment() == null || row.getPayment().getPaymentMethod() == null) {
                    continue;
                }

                Double amount = safeDouble(row.getPayment().getPaidValue());  // Ensure amounts are not null
                PaymentMethod method = row.getPayment().getPaymentMethod();  // Using the enum type directly
                total+=amount;
                System.out.println("method = " + method);
                System.out.println("amount = " + amount);
                switch (method) {
                    case Agent:
                        this.agentValue += amount;
                        this.hasAgentTransaction = true;
                        break;
                    case Card:
                        this.cardValue += amount;
                        this.hasCardTransaction = true;
                        break;
                    case Cash:
                        this.cashValue += amount;
                        this.hasCashTransaction = true;
                        break;
                    case Cheque:
                        this.chequeValue += amount;
                        this.hasChequeTransaction = true;
                        break;
                    case Credit:
                        this.creditValue += amount;
                        this.hasCreditTransaction = true;
                        break;
                    case IOU:
                        this.iouValue += amount;
                        this.hasIouTransaction = true;
                        break;
                    case MultiplePaymentMethods:
                        this.multiplePaymentMethodsValue += amount;
                        this.hasMultiplePaymentMethodsTransaction = true;
                        break;
                    case OnlineSettlement:
                        this.onlineSettlementValue += amount;
                        this.hasOnlineSettlementTransaction = true;
                        break;
                    case PatientDeposit:
                        this.patientDepositValue += amount;
                        this.hasPatientDepositTransaction = true;
                        break;
                    case PatientPoints:
                        this.patientPointsValue += amount;
                        this.hasPatientPointsTransaction = true;
                        break;
                    case Slip:
                        this.slipValue += amount;
                        this.hasSlipTransaction = true;
                        break;
                    case Staff:
                        this.staffValue += amount;
                        this.hasStaffTransaction = true;
                        break;
                    case Staff_Welfare:
                        this.staffWelfareValue += amount;
                        this.hasStaffWelfareTransaction = true;
                        break;
                    case Voucher:
                        this.voucherValue += amount;
                        this.hasVoucherTransaction = true;
                        break;
                    case YouOweMe:
                        this.iouValue += amount;  // Assuming YouOweMe is equivalent to IOU
                        this.hasIouTransaction = true;
                        break;
                    case ewallet:
                        this.eWalletValue += amount;
                        this.hasEWalletTransaction = true;
                        break;
                    default:
                        // Log unexpected payment method or handle error
                        System.out.println("Unhandled payment method: " + method);
                        break;
                }
            }
        }
    }

    public void createRowValuesFromBill() {
        if (this.reportTemplateRows != null && !this.reportTemplateRows.isEmpty()) {
            for (ReportTemplateRow row : this.reportTemplateRows) {
                if (row.getBill() == null) {
                    continue;
                }
                row.setGrossTotal(row.getBill().getGrantTotal());
                row.setDiscount(row.getBill().getDiscount());
                row.setTotal(row.getBill().getNetTotal());
                row.setHospitalTotal(row.getHospitalTotal());
                row.setStaffTotal(row.getBill().getTotalStaffFee());
                row.setCcTotal(row.getBill().getTotalCenterFee());
            }
        }
    }

    private void resetTotalsAndFlags() {
        // Reset all financial values and transaction flags
        this.cashValue = this.cardValue = this.multiplePaymentMethodsValue = this.staffValue
                = this.creditValue = this.staffWelfareValue = this.voucherValue = this.iouValue
                = this.agentValue = this.chequeValue = this.slipValue = this.eWalletValue
                = this.patientDepositValue = this.patientPointsValue = this.onlineSettlementValue
                = this.grossTotal = this.discount = this.total
                = this.hospitalTotal = this.staffTotal = this.ccTotal = 0.0;

        this.hasCashTransaction = this.hasCardTransaction = this.hasMultiplePaymentMethodsTransaction = this.hasStaffTransaction
                = this.hasCreditTransaction = this.hasStaffWelfareTransaction = this.hasVoucherTransaction = this.hasIouTransaction
                = this.hasAgentTransaction = this.hasChequeTransaction = this.hasSlipTransaction = this.hasEWalletTransaction
                = this.hasPatientDepositTransaction = this.hasPatientPointsTransaction = this.hasOnlineSettlementTransaction = false;
    }

    private void addValueAndUpdateFlag(String calculationAttribute, double amount) {
        System.out.println("addValueAndUpdateFlag");
        System.out.println("amount = " + amount);
        System.out.println("calculationAttribute = " + calculationAttribute);

        if (amount != 0) {
            switch (calculationAttribute) {
                case "cash":
                    this.cashValue += amount;
                    this.hasCashTransaction = true;
                    break;
                case "card":
                    this.cardValue += amount;
                    this.hasCardTransaction = true;
                    break;
                case "multiplePaymentMethods":
                    this.multiplePaymentMethodsValue += amount;
                    this.hasMultiplePaymentMethodsTransaction = true;
                    break;
                case "staff":
                    this.staffValue += amount;
                    this.hasStaffTransaction = true;
                    break;
                case "credit":
                    this.creditValue += amount;
                    this.hasCreditTransaction = true;
                    break;
                case "staffWelfare":
                    this.staffWelfareValue += amount;
                    this.hasStaffWelfareTransaction = true;
                    break;
                case "voucher":
                    this.voucherValue += amount;
                    this.hasVoucherTransaction = true;
                    break;
                case "iou":
                    this.iouValue += amount;
                    this.hasIouTransaction = true;
                    break;
                case "agent":
                    this.agentValue += amount;
                    this.hasAgentTransaction = true;
                    break;
                case "cheque":
                    this.chequeValue += amount;
                    this.hasChequeTransaction = true;
                    break;
                case "slip":
                    this.slipValue += amount;
                    this.hasSlipTransaction = true;
                    break;
                case "Wallet":
                    this.eWalletValue += amount;
                    this.hasEWalletTransaction = true;
                    break;
                case "patientDeposit":
                    this.patientDepositValue += amount;
                    this.hasPatientDepositTransaction = true;
                    break;
                case "patientPoints":
                    this.patientPointsValue += amount;
                    this.hasPatientPointsTransaction = true;
                    break;
                case "onlineSettlement":
                    this.onlineSettlementValue += amount;
                    this.hasOnlineSettlementTransaction = true;
                    break;
                case "grossTotal":
                    this.grossTotal += amount;
                    break;
                case "discount":
                    this.discount += amount;
                    break;
                case "total":
                    this.total += amount;
                    break;
                case "hospitalTotal":
                    this.hospitalTotal += amount;
                    break;
                case "staffTotal":
                    this.staffTotal += amount;
                    break;
                case "ccTotal":
                    this.ccTotal += amount;
                    break;
                default:
                    // Log unexpected payment method or handle error
                    break;
            }
        }
    }

    private double safeDouble(Double value) {
        return value == null ? 0.0 : value;
    }

    public double getOnCallValue() {
        return onCallValue;
    }

    public void setOnCallValue(double onCallValue) {
        this.onCallValue = onCallValue;
    }

    public double getCashValue() {
        return cashValue;
    }

    public void setCashValue(double cashValue) {
        this.cashValue = cashValue;
    }

    public double getCardValue() {
        return cardValue;
    }

    public void setCardValue(double cardValue) {
        this.cardValue = cardValue;
    }

    public double getMultiplePaymentMethodsValue() {
        return multiplePaymentMethodsValue;
    }

    public void setMultiplePaymentMethodsValue(double multiplePaymentMethodsValue) {
        this.multiplePaymentMethodsValue = multiplePaymentMethodsValue;
    }

    public double getStaffValue() {
        return staffValue;
    }

    public void setStaffValue(double staffValue) {
        this.staffValue = staffValue;
    }

    public double getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(double creditValue) {
        this.creditValue = creditValue;
    }

    public double getStaffWelfareValue() {
        return staffWelfareValue;
    }

    public void setStaffWelfareValue(double staffWelfareValue) {
        this.staffWelfareValue = staffWelfareValue;
    }

    public double getVoucherValue() {
        return voucherValue;
    }

    public void setVoucherValue(double voucherValue) {
        this.voucherValue = voucherValue;
    }

    public double getIouValue() {
        return iouValue;
    }

    public void setIouValue(double iouValue) {
        this.iouValue = iouValue;
    }

    public double getAgentValue() {
        return agentValue;
    }

    public void setAgentValue(double agentValue) {
        this.agentValue = agentValue;
    }

    public double getChequeValue() {
        return chequeValue;
    }

    public void setChequeValue(double chequeValue) {
        this.chequeValue = chequeValue;
    }

    public double getSlipValue() {
        return slipValue;
    }

    public void setSlipValue(double slipValue) {
        this.slipValue = slipValue;
    }

    public double getEwalletValue() {
        return eWalletValue;
    }

    public void setEwalletValue(double eWalletValue) {
        this.eWalletValue = eWalletValue;
    }

    public double getPatientDepositValue() {
        return patientDepositValue;
    }

    public void setPatientDepositValue(double patientDepositValue) {
        this.patientDepositValue = patientDepositValue;
    }

    public double getPatientPointsValue() {
        return patientPointsValue;
    }

    public void setPatientPointsValue(double patientPointsValue) {
        this.patientPointsValue = patientPointsValue;
    }

    public double getOnlineSettlementValue() {
        return onlineSettlementValue;
    }

    public void setOnlineSettlementValue(double onlineSettlementValue) {
        this.onlineSettlementValue = onlineSettlementValue;
    }

    public ReportTemplate getReportTemplate() {
        return reportTemplate;
    }

    public void setReportTemplate(ReportTemplate reportTemplate) {
        this.reportTemplate = reportTemplate;
    }

    public List<ReportTemplateRow> getReportTemplateRows() {
        if (reportTemplateRows == null) {
            reportTemplateRows = new ArrayList<>();
        }
        return reportTemplateRows;
    }

    public void setReportTemplateRows(List<ReportTemplateRow> reportTemplateRows) {
        this.reportTemplateRows = reportTemplateRows;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(Double totalIn) {
        this.totalIn = totalIn;
    }

    public Double getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(Double totalOut) {
        this.totalOut = totalOut;
    }

    public Long getCountIn() {
        return countIn;
    }

    public void setCountIn(Long countIn) {
        this.countIn = countIn;
    }

    public Long getCountOut() {
        return countOut;
    }

    public void setCountOut(Long countOut) {
        this.countOut = countOut;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SessionInstance getSessionInstance() {
        return sessionInstance;
    }

    public void setSessionInstance(SessionInstance sessionInstance) {
        this.sessionInstance = sessionInstance;
    }

    public Long getLong1() {
        return long1;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public Long getLong2() {
        return long2;
    }

    public void setLong2(Long long2) {
        this.long2 = long2;
    }

    public Long getLong3() {
        return long3;
    }

    public void setLong3(Long long3) {
        this.long3 = long3;
    }

    public Long getLong4() {
        return long4;
    }

    public void setLong4(Long long4) {
        this.long4 = long4;
    }

    public Long getLong5() {
        return long5;
    }

    public void setLong5(Long long5) {
        this.long5 = long5;
    }

    public Long getLong6() {
        return long6;
    }

    public void setLong6(Long long6) {
        this.long6 = long6;
    }

    public Long getLong7() {
        return long7;
    }

    public void setLong7(Long long7) {
        this.long7 = long7;
    }

    public Long getLong8() {
        return long8;
    }

    public void setLong8(Long long8) {
        this.long8 = long8;
    }

    public Long getLong9() {
        return long9;
    }

    public void setLong9(Long long9) {
        this.long9 = long9;
    }

    public Long getLong10() {
        return long10;
    }

    public void setLong10(Long long10) {
        this.long10 = long10;
    }

    public double geteWalletValue() {
        return eWalletValue;
    }

    public void seteWalletValue(double eWalletValue) {
        this.eWalletValue = eWalletValue;
    }

    public boolean isHasOnCallTransaction() {
        return hasOnCallTransaction;
    }

    public void setHasOnCallTransaction(boolean hasOnCallTransaction) {
        this.hasOnCallTransaction = hasOnCallTransaction;
    }

    public boolean isHasCashTransaction() {
        return hasCashTransaction;
    }

    public void setHasCashTransaction(boolean hasCashTransaction) {
        this.hasCashTransaction = hasCashTransaction;
    }

    public boolean isHasCardTransaction() {
        return hasCardTransaction;
    }

    public void setHasCardTransaction(boolean hasCardTransaction) {
        this.hasCardTransaction = hasCardTransaction;
    }

    public boolean isHasMultiplePaymentMethodsTransaction() {
        return hasMultiplePaymentMethodsTransaction;
    }

    public void setHasMultiplePaymentMethodsTransaction(boolean hasMultiplePaymentMethodsTransaction) {
        this.hasMultiplePaymentMethodsTransaction = hasMultiplePaymentMethodsTransaction;
    }

    public boolean isHasStaffTransaction() {
        return hasStaffTransaction;
    }

    public void setHasStaffTransaction(boolean hasStaffTransaction) {
        this.hasStaffTransaction = hasStaffTransaction;
    }

    public boolean isHasCreditTransaction() {
        return hasCreditTransaction;
    }

    public void setHasCreditTransaction(boolean hasCreditTransaction) {
        this.hasCreditTransaction = hasCreditTransaction;
    }

    public boolean isHasStaffWelfareTransaction() {
        return hasStaffWelfareTransaction;
    }

    public void setHasStaffWelfareTransaction(boolean hasStaffWelfareTransaction) {
        this.hasStaffWelfareTransaction = hasStaffWelfareTransaction;
    }

    public boolean isHasVoucherTransaction() {
        return hasVoucherTransaction;
    }

    public void setHasVoucherTransaction(boolean hasVoucherTransaction) {
        this.hasVoucherTransaction = hasVoucherTransaction;
    }

    public boolean isHasIouTransaction() {
        return hasIouTransaction;
    }

    public void setHasIouTransaction(boolean hasIouTransaction) {
        this.hasIouTransaction = hasIouTransaction;
    }

    public boolean isHasAgentTransaction() {
        return hasAgentTransaction;
    }

    public void setHasAgentTransaction(boolean hasAgentTransaction) {
        this.hasAgentTransaction = hasAgentTransaction;
    }

    public boolean isHasChequeTransaction() {
        return hasChequeTransaction;
    }

    public void setHasChequeTransaction(boolean hasChequeTransaction) {
        this.hasChequeTransaction = hasChequeTransaction;
    }

    public boolean isHasSlipTransaction() {
        return hasSlipTransaction;
    }

    public void setHasSlipTransaction(boolean hasSlipTransaction) {
        this.hasSlipTransaction = hasSlipTransaction;
    }

    public boolean isHasEWalletTransaction() {
        return hasEWalletTransaction;
    }

    public void setHasEWalletTransaction(boolean hasEWalletTransaction) {
        this.hasEWalletTransaction = hasEWalletTransaction;
    }

    public boolean isHasPatientDepositTransaction() {
        return hasPatientDepositTransaction;
    }

    public void setHasPatientDepositTransaction(boolean hasPatientDepositTransaction) {
        this.hasPatientDepositTransaction = hasPatientDepositTransaction;
    }

    public boolean isHasPatientPointsTransaction() {
        return hasPatientPointsTransaction;
    }

    public void setHasPatientPointsTransaction(boolean hasPatientPointsTransaction) {
        this.hasPatientPointsTransaction = hasPatientPointsTransaction;
    }

    public boolean isHasOnlineSettlementTransaction() {
        return hasOnlineSettlementTransaction;
    }

    public void setHasOnlineSettlementTransaction(boolean hasOnlineSettlementTransaction) {
        this.hasOnlineSettlementTransaction = hasOnlineSettlementTransaction;
    }

    public String getBundleType() {
        return bundleType;
    }

    public void setBundleType(String bundleType) {
        this.bundleType = bundleType;
    }

    public List<ReportTemplateRowBundle> getBundles() {
        if (bundles == null) {
            bundles = new ArrayList<>();
        }
        return bundles;
    }

    public void setBundles(List<ReportTemplateRowBundle> bundles) {
        this.bundles = bundles;
    }

    public Double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public Double getHospitalTotal() {
        return hospitalTotal;
    }

    public void setHospitalTotal(Double hospitalTotal) {
        this.hospitalTotal = hospitalTotal;
    }

    public Double getStaffTotal() {
        return staffTotal;
    }

    public void setStaffTotal(Double staffTotal) {
        this.staffTotal = staffTotal;
    }

    public Double getCcTotal() {
        return ccTotal;
    }

    public void setCcTotal(Double ccTotal) {
        this.ccTotal = ccTotal;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Bill getStartBill() {
        return startBill;
    }

    public void setStartBill(Bill startBill) {
        this.startBill = startBill;
    }

    public Bill getEndBill() {
        return endBill;
    }

    public void setEndBill(Bill endBill) {
        this.endBill = endBill;
    }

}
