package com.example.hotcode_bankingsolution.entity;

import com.example.hotcode_bankingsolution.dto.AccountDTO;
import com.example.hotcode_bankingsolution.enums.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.example.hotcode_bankingsolution.entity.Account.mapToAccountDTOsList;
import static com.example.hotcode_bankingsolution.entity.Account.newAccount;
import static com.example.hotcode_bankingsolution.enums.Currency.USD;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class AccountTest {

    private static Stream<Arguments> createNewAccountTest() {
        return Stream.of(
                Arguments.of(
                        new AccountDTO(
                                null,
                                null,
                                "Serhiy Honchar",
                                null,
                                null,
                                null
                        ),
                        new Account(
                                null,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Serhiy Honchar",
                                ZERO,
                                USD,
                                LocalDateTime.of(2024, 10, 4, 15, 52)
                        )
                )
        );
    }

    private static Stream<Arguments> updateTest() {
        return Stream.of(
                Arguments.of(
                        new Account(
                                3L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Serhiy Honchar",
                                new BigDecimal("225"),
                                USD,
                                LocalDateTime.of(2024, 10, 4, 15, 52)
                        ),
                        new Account(
                                6L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Oleh",
                                new BigDecimal("645"),
                                USD,
                                LocalDateTime.of(2024, 5, 4, 15, 52)
                        ),
                        new Account(
                                6L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Oleh",
                                new BigDecimal("645"),
                                USD,
                                LocalDateTime.of(2024, 5, 4, 15, 52)
                        )
                ),
                Arguments.of(
                        new Account(
                                3L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Serhiy Honchar",
                                new BigDecimal("225"),
                                USD,
                                LocalDateTime.of(2024, 10, 4, 15, 52)
                        ),
                        new Account(
                                null,
                                null,
                                null,
                                null,
                                null,
                                null
                        ),
                        new Account(
                                3L,
                                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                                "Serhiy Honchar",
                                new BigDecimal("225"),
                                USD,
                                LocalDateTime.of(2024, 10, 4, 15, 52)
                        )
                )
        );
    }

    @Test
    void createNewAccountWithConstructorTest() {
        // Arrange
        var localDateTime = LocalDateTime.now();

        // Act
        var actual = new Account(
                "20670b23-2be2-4ab4-aada-7e394cf689bc",
                "Serhiy Honchar",
                new BigDecimal("32"),
                USD,
                localDateTime
        );

        // Assert
        assertThat(actual).isEqualTo(
                new Account(
                        "20670b23-2be2-4ab4-aada-7e394cf689bc",
                        "Serhiy Honchar",
                        new BigDecimal("32"),
                        USD,
                        localDateTime
                )
        );
    }

    @Test
    void mapFromAccountDTOTest() {
        // Arrange
        var accountDTO = new AccountDTO(
                null,
                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                "Serhiy Honchar",
                ZERO,
                USD,
                LocalDateTime.of(2024, 10, 4, 15, 52)
        );
        var expected = new Account(
                null,
                "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                "Serhiy Honchar",
                ZERO,
                USD,
                LocalDateTime.of(2024, 10, 4, 15, 52)
        );

        // Act
        var actual = new Account(accountDTO);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createNewAccountTest")
    void createNewAccountTest(AccountDTO accountDTO, Account expected) {
        var localDateTime = LocalDateTime.of(2024, 10, 4, 15, 52);
        var id = UUID.fromString("899a5f07-338f-4fc3-aec0-f3876cd196f5");
        try (
                var mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class);
                var mockedUUID = Mockito.mockStatic(UUID.class)
        ) {
            // Arrange
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(localDateTime);
            mockedUUID.when(UUID::randomUUID).thenReturn(id);

            // Act
            var actual = newAccount(accountDTO);

            // Assert
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    void mapToAccountDTOsListTest() {
        // Arrange
        var accounts = List.of(
                new Account(
                        null,
                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                        "Serhiy Honchar",
                        ZERO,
                        USD,
                        LocalDateTime.of(2024, 10, 4, 15, 52)
                )
        );
        var expected = List.of(
                new AccountDTO(
                        null,
                        "899a5f07-338f-4fc3-aec0-f3876cd196f5",
                        "Serhiy Honchar",
                        ZERO,
                        USD,
                        LocalDateTime.of(2024, 10, 4, 15, 52)
                )
        );

        // Act
        var actual = mapToAccountDTOsList(accounts);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("updateTest")
    void updateTest(
            Account accountForUpdate,
            Account updateData,
            Account expected
    ) {
        // Act
        var actual = accountForUpdate.update(updateData);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
