package com.example.finance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    private String description;

    @Column(nullable = false)
    @Positive
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public void setDescription(@Size(min = 1, max = 255) String description) {
        this.description = description;
    }

    public void setAmount(@Positive BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(@NotNull TransactionType type) {
        this.type = type;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public void setUser(@NotNull User user) {
        this.user = user;
    }
}