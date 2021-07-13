package org.mk.dentisteapp.entities.data;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ChatMessage {
    private String content;
    private String sender;
    private String type;
}
