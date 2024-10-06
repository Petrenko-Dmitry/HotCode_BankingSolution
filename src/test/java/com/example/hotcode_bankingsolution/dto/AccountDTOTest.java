package com.example.hotcode_bankingsolution.dto;

import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.enums.Currency;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.example.hotcode_bankingsolution.enums.Currency.USD;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountDTOTest {

    private static Stream<Arguments> createNewAccountDTOTest() {
        return Stream.of(
                Arguments.of(
                        new Account(
                                1L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Serhiy Honchar",
                                ZERO,
                                USD,
                                LocalDateTime.of(2024, 10, 4, 15, 52)
                        ),
                        new AccountDTO(
                                1L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Serhiy Honchar",
                                ZERO,
                                USD,
                                LocalDateTime.of(2024, 10, 4, 15, 52)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("createNewAccountDTOTest")
    void createNewAccountDTOTest(Account account, AccountDTO expected) {
        // Act
        var actual = new AccountDTO(account);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
