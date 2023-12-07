package org.group12.model.Calendar;

import java.util.List;

public interface ITagable {
    void removeTag(String tag);
    void addTag(String tag);
    List<String> getTags();
}
