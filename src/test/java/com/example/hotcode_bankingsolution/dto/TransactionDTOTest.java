package com.example.hotcode_bankingsolution.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;

import static com.example.hotcode_bankingsolution.enums.TransactionType.DEPOSIT;
import static java.math.BigDecimal.ZERO;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionDTOTest {

    private static Set<Arguments> isAccountNumberEqualsToAccountNumberTest() {
        return Set.of(
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                        true
                ),
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                        false
                )
        );
    }

    private static Set<Arguments> isAccountNumberEqualsFromAccountNumberTest() {
        return Set.of(
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                        false
                ),
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                        true
                )
        );
    }

    private static Set<Arguments> isAccountNumberEqualsFromOrToAccountNumberTest() {
        return Set.of(
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                        true
                ),
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                        true
                ),
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "cb9d0e01-c97d-457f-9dfd-ed9d19452356",
                        false
                ),
                Arguments.of(
                        new TransactionDTO(
                                DEPOSIT,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                ZERO
                        ),
                        "899a5f07-338f-4fc3-aec0-f3876cd196f6",
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("isAccountNumberEqualsToAccountNumberTest")
    void isAccountNumberEqualsToAccountNumberTest(
            TransactionDTO transactionDTO,
            String accountNumber,
            boolean expected
    ) {
        // Act
        var actual = transactionDTO.isAccountNumberEqualsToAccountNumber(accountNumber);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }


    @ParameterizedTest
    @MethodSource("isAccountNumberEqualsFromAccountNumberTest")
    void isAccountNumberEqualsFromAccountNumberTest(
            TransactionDTO transactionDTO,
            String accountNumber,
            boolean expected
    ) {
        // Act
        var actual = transactionDTO.isAccountNumberEqualsFromAccountNumber(accountNumber);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("isAccountNumberEqualsFromOrToAccountNumberTest")
    void isAccountNumberEqualsFromOrToAccountNumberTest(
            TransactionDTO transactionDTO,
            String accountNumber,
            boolean expected
    ) {
        // Act
        var actual = transactionDTO.isAccountNumberEqualsFromOrToAccountNumber(accountNumber);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
