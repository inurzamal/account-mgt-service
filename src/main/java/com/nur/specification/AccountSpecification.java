package com.nur.specification;

import com.nur.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<Account> hasAccountNumber(String accountNumber) {
        return (root, query, criteriaBuilder) ->
                accountNumber == null ? null : criteriaBuilder.equal(root.get("accountNumber"), accountNumber);
    }

    public static Specification<Account> hasAccountHolderName(String accountHolderName) {
        return (root, query, criteriaBuilder) ->
                accountHolderName == null ? null : criteriaBuilder.like(root.get("accountHolderName"), "%" + accountHolderName + "%");
    }

    public static Specification<Account> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Account> hasPhoneNo(String phoneNo) {
        return (root, query, criteriaBuilder) ->
                phoneNo == null ? null : criteriaBuilder.equal(root.get("phoneNo"), phoneNo);
    }

    public static Specification<Account> hasPan(String pan) {
        return (root, query, criteriaBuilder) ->
                pan == null ? null : criteriaBuilder.equal(root.get("pan"), pan);
    }
}
