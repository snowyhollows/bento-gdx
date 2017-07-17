package net.snowyhollows.bento.gdx.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class StringMapper implements Map<String, String> {

    private final String[] names;

    public StringMapper(String... names) {
        this.names = names;
    }

    public static StringMapper of(String... names) {
        return new StringMapper(names);
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String get(Object key) {
        String shortName = key.toString();
        for (int i = 0; i < names.length; i += 2) {
            if (names[i].equals(shortName)) {
                return names[i + 1];
            }
        }
        return null;
    }

    @Override
    public String put(String key, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
