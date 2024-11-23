package com.example.cbox.dto.create;

import com.example.cbox.enumeration.Status;

public record Message(
        String senderName,
        String receiverName,
        String message,
        String date,
        Status status
) {
}
