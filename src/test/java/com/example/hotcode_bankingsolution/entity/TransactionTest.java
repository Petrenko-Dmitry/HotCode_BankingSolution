package com.example.hotcode_bankingsolution.entity;

import com.example.hotcode_bankingsolution.dto.TransactionDTO;
import com.example.hotcode_bankingsolution.enums.TransactionType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    private static Stream<Arguments> createNewTransactionTest() {
        return Stream.of(
                Arguments.of(
                        new TransactionDTO(
                                TransactionType.DEPOSIT,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                EMPTY,
                                EMPTY,
                                new BigDecimal("5.04")
                        ),
                        new Transaction(
                                null,
                                TransactionType.DEPOSIT,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                EMPTY,
                                EMPTY,
                                new BigDecimal("5.04")
                        )
                ),
                Arguments.of(
                        new TransactionDTO(
                                TransactionType.WITHDRAWAL,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                EMPTY,
                                EMPTY,
                                new BigDecimal("25.04")
                        ),
                        new Transaction(
                                null,
                                TransactionType.WITHDRAWAL,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                EMPTY,
                                EMPTY,
                                new BigDecimal("25.04")
                        )
                ),
                Arguments.of(
                        new TransactionDTO(
                                TransactionType.TRANSFER,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                new BigDecimal("15.04")
                        ),
                        new Transaction(
                                null,
                                TransactionType.TRANSFER,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                new BigDecimal("15.04")
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("createNewTransactionTest")
    void createNewTransactionTest(TransactionDTO transactionDTO, Transaction expected) {
        // Act
        var actual = new Transaction(transactionDTO);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
