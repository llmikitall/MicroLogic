package ru.project.Core;

import java.util.List;

public interface LogElement {
    List<LogElement> getInput();
    LogElement getInputIndex(int index);
    void setInput(List<LogElement> input);
    void setInputIndex(LogElement input);
    void setOutput(LogElement output);
    LogElement getOutput();
    boolean isState();
    boolean simulation();
}
