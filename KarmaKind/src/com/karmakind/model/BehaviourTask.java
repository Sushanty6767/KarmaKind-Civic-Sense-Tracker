package com.karmakind.model;

public class BehaviourTask extends Task {
    private String tag;

    public BehaviourTask(String name, String tag) {
        super(name);
        this.taskType = "BEHAVIOUR";
        this.tag = tag;
    }

    @Override
    public int calculateKarma() {
        // higher civic-impact tasks can carry more karma
        int multiplier = "community".equalsIgnoreCase(tag) ? 3 : 1;
        return karmaValue * multiplier;
    }

    public String getTag(){ return tag; }
}
