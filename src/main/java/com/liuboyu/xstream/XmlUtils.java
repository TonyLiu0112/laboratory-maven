package com.liuboyu.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.Writer;

/**
 * @author wuwenming
 */
public class XmlUtils {
    // private static Logger logger = LoggerFactory
    // .getLogger(XmlUtils.class);
    public static String ObjToXml(String alias, Object obj) {
        XStream xstream = new XStream(new XppDriverImpl());
        xstream.alias(alias, obj.getClass());
        xstream.autodetectAnnotations(true);
        xstream.omitField(obj.getClass(), "MsgId");
        // 将对象转换为XML字符串
        String xml = xstream.toXML(obj);
        return xml;
    }

    public static void main(String[] args) {
        String xml = "<xml><ToUserName>gh_7f083739789a</ToUserName></xml>"; // <FromUserName>oia2TjuEGTNoeX76QEjQNrcURxG8</FromUserName><CreateTime>1395658920</CreateTime><MsgType>event</MsgType><Event>TEMPLATESENDJOBFINISH</Event><MsgID>200163836</MsgID><Status>success</Status></xml>
        XmlUtils.xmlToObjIgnoreFieldError("xml", xml);
    }

    public static TransferComplexMsg xmlToObjIgnoreFieldError(String alias, String xml) {

        XStream xs = new XStream(new DomDriver()) {
            // 忽略不存在的属性
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {

                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        xs.autodetectAnnotations(true);
        xs.alias(alias, TransferComplexMsg.class);
        xs.autodetectAnnotations(true);
        Object o = xs.fromXML(xml);

        System.out.println("---" + o + " ****-->" + (o instanceof TransferComplexMsg));
        // TransferComplexMsg mo =
        // JsonUtils.fromJson(JsonUtils.toJson(o),TransferComplexMsg.class) ;
        TransferComplexMsg m = (TransferComplexMsg) o;
        TransferComplexMsg m2 = TransferComplexMsg.class.cast(o);

        return m;
    }

    public static Object xmlToObjIgnoreFieldError(String alias, String xml,
                                                  Class<?> t) {

        XStream xs = new XStream(new DomDriver()) {
            // 忽略不存在的属性
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        xs.alias(alias, t);
        xs.autodetectAnnotations(true);
        return xs.fromXML(xml.toString());
    }

    public static Object xmlToObj(String alias, String xml, Class<?> t) {
        XStream xs = new XStream(new DomDriver());
        xs.alias(alias, t);
        xs.autodetectAnnotations(true);
        return xs.fromXML(xml.toString());
    }

}

class XppDriverImpl extends XppDriver {
    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out) {
            @Override
            protected void writeText(QuickWriter writer, String text) {
                if (!text.startsWith("<![CDATA[")) {
                    text = "<![CDATA[" + text + "]]>";
                }
                writer.write(text);
            }
        };
    }

}
