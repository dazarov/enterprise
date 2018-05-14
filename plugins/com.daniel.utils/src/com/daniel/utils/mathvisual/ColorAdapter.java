package com.daniel.utils.mathvisual;

import java.awt.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ColorAdapter extends XmlAdapter<String, Color>{

public Color unmarshal(String v) throws Exception {
    return new Color((int)Long.parseLong(v, 16));
}

public String marshal(Color v) throws Exception {
    return Integer.toHexString(v.getRGB());
}

}