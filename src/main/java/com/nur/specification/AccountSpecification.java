package com.nur.specification;

import com.nur.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> hasAccountNumber(String accountNumber) {
        return (root, query, criteriaBuilder) ->
                accountNumber == null ? null : criteriaBuilder.equal(root.get("accountNumber"), accountNumber);
    }

    public static Specification<Account> hasCustomerId(Long customerId) {
        return (root, query, criteriaBuilder) ->
                customerId == null ? null : criteriaBuilder.equal(root.get("customerId"), customerId);
    }

}
