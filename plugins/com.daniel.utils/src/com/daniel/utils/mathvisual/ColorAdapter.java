package com.daniel.utils.mathvisual;

import java.awt.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ColorAdapter extends XmlAdapter<String, Color>{

public Color unmarshal(String v) throws Exception {
    return new Color(Integer.valueOf(v));
}

public String marshal(Color v) throws Exception {
    return "" + v.getRGB();
}

}