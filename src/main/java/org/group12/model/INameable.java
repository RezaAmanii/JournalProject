package org.group12.model;

import java.time.LocalDateTime;

interface INameable {
    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String desc);

    LocalDateTime getDateCreated();
}
