package com.example.hotcode_bankingsolution.enums;

import com.example.hotcode_bankingsolution.dto.TransactionDTO;
import com.example.hotcode_bankingsolution.entity.Account;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static com.example.hotcode_bankingsolution.enums.Currency.USD;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTypeTest {
    private static Stream<Arguments> transactionTypeProcessTest() {
        var creationDate = LocalDateTime.now();
        return Stream.of(
                Arguments.of(
                        new TransactionDTO(
                                TransactionType.DEPOSIT,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                EMPTY,
                                EMPTY,
                                new BigDecimal("13.324")
                        ),
                        List.of(
                                new Account(
                                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                        EMPTY,
                                        new BigDecimal("3"),
                                        USD,
                                        creationDate
                                )
                        ),
                        List.of(
                                new Account(
                                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                        EMPTY,
                                        new BigDecimal("16.324"),
                                        USD,
                                        creationDate
                                )
                        )
                ),
                Arguments.of(
                        new TransactionDTO(
                                TransactionType.WITHDRAWAL,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                EMPTY,
                                EMPTY,
                                new BigDecimal("13.324")
                        ),
                        List.of(
                                new Account(
                                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                        EMPTY,
                                        new BigDecimal("24"),
                                        USD,
                                        creationDate
                                )
                        ),
                        List.of(
                                new Account(
                                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                        EMPTY,
                                        new BigDecimal("10.676"),
                                        USD,
                                        creationDate
                                )
                        )
                ),
                Arguments.of(
                        new TransactionDTO(
                                TransactionType.TRANSFER,
                                EMPTY,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                new BigDecimal("13.324")
                        ),
                        List.of(
                                new Account(
                                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                        EMPTY,
                                        new BigDecimal("24"),
                                        USD,
                                        creationDate
                                ),
                                new Account(
                                        "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                        EMPTY,
                                        new BigDecimal("24"),
                                        USD,
                                        creationDate
                                )
                        ),
                        List.of(
                                new Account(
                                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                        EMPTY,
                                        new BigDecimal("10.676"),
                                        USD,
                                        creationDate
                                ),
                                new Account(
                                        "cb9d0e01-c97d-457f-9dfd-ed9d19452357",
                                        EMPTY,
                                        new BigDecimal("37.324"),
                                        USD,
                                        creationDate
                                )
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("transactionTypeProcessTest")
    void transactionTypeProcessTest(
            TransactionDTO transactionDTO,
            List<Account> accounts,
            List<Account> expected
    ) {
        // Act
        var actual = transactionDTO.transactionType().process(accounts, transactionDTO);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
