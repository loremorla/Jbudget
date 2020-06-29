package it.unicam.cs.pa.jbudget105124.Model.Tag;

public interface TagManager {
    static Tag createTag(String name,int ID) {
        return new TagSingle(name,ID);
    }
}