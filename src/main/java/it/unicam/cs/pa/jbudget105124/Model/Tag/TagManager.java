package it.unicam.cs.pa.jbudget105124.Model.Tag;

/**
 * Interfaccia che ha il compito di creare un Tag.
 */
public interface TagManager {

    /**
     * Metodo per generare un TagSingle
     * @param name
     * @param ID
     * @return TagSingle generato
     */
    static Tag createTag(String name,int ID) {
        return new TagSingle(name,ID);
    }
}