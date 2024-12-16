package com.wg.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	private String id;
	private String content;
	private String type;
	private String receiver;
	private LocalDateTime createdAt;
}