package com.javadi.websitecrawler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Map file extensions to MIME types. Based on the Apache mime.types file.
 * http://www.iana.org/assignments/media-types/
 */
public class MimeTypes {

    public static final String MIME_APPLICATION_ANDREW_INSET = "application/andrew-inset";
    public static final String MIME_APPLICATION_JSON = "application/json";
    public static final String MIME_APPLICATION_ZIP = "application/zip";
    public static final String MIME_APPLICATION_X_GZIP = "application/x-gzip";
    public static final String MIME_APPLICATION_TGZ = "application/tgz";
    public static final String MIME_APPLICATION_MSWORD = "application/msword";
    public static final String MIME_APPLICATION_MSWORD_2007 = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_APPLICATION_VND_TEXT = "application/vnd.oasis.opendocument.text";
    public static final String MIME_APPLICATION_POSTSCRIPT = "application/postscript";
    public static final String MIME_APPLICATION_PDF = "application/pdf";
    public static final String MIME_APPLICATION_JNLP = "application/jnlp";
    public static final String MIME_APPLICATION_MAC_BINHEX40 = "application/mac-binhex40";
    public static final String MIME_APPLICATION_MAC_COMPACTPRO = "application/mac-compactpro";
    public static final String MIME_APPLICATION_MATHML_XML = "application/mathml+xml";
    public static final String MIME_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String MIME_APPLICATION_ODA = "application/oda";
    public static final String MIME_APPLICATION_RDF_XML = "application/rdf+xml";
    public static final String MIME_APPLICATION_JAVA_ARCHIVE = "application/java-archive";
    public static final String MIME_APPLICATION_RDF_SMIL = "application/smil";
    public static final String MIME_APPLICATION_SRGS = "application/srgs";
    public static final String MIME_APPLICATION_SRGS_XML = "application/srgs+xml";
    public static final String MIME_APPLICATION_VND_MIF = "application/vnd.mif";
    public static final String MIME_APPLICATION_VND_MSEXCEL = "application/vnd.ms-excel";
    public static final String MIME_APPLICATION_VND_MSEXCEL_2007 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MIME_APPLICATION_VND_SPREADSHEET = "application/vnd.oasis.opendocument.spreadsheet";
    public static final String MIME_APPLICATION_VND_MSPOWERPOINT = "application/vnd.ms-powerpoint";
    public static final String MIME_APPLICATION_VND_RNREALMEDIA = "application/vnd.rn-realmedia";
    public static final String MIME_APPLICATION_X_BCPIO = "application/x-bcpio";
    public static final String MIME_APPLICATION_X_CDLINK = "application/x-cdlink";
    public static final String MIME_APPLICATION_X_CHESS_PGN = "application/x-chess-pgn";
    public static final String MIME_APPLICATION_X_CPIO = "application/x-cpio";
    public static final String MIME_APPLICATION_X_CSH = "application/x-csh";
    public static final String MIME_APPLICATION_X_DIRECTOR = "application/x-director";
    public static final String MIME_APPLICATION_X_DVI = "application/x-dvi";
    public static final String MIME_APPLICATION_X_FUTURESPLASH = "application/x-futuresplash";
    public static final String MIME_APPLICATION_X_GTAR = "application/x-gtar";
    public static final String MIME_APPLICATION_X_HDF = "application/x-hdf";
    public static final String MIME_APPLICATION_X_JAVASCRIPT = "application/x-javascript";
    public static final String MIME_APPLICATION_X_KOAN = "application/x-koan";
    public static final String MIME_APPLICATION_X_LATEX = "application/x-latex";
    public static final String MIME_APPLICATION_X_NETCDF = "application/x-netcdf";
    public static final String MIME_APPLICATION_X_OGG = "application/x-ogg";
    public static final String MIME_APPLICATION_X_SH = "application/x-sh";
    public static final String MIME_APPLICATION_X_SHAR = "application/x-shar";
    public static final String MIME_APPLICATION_X_SHOCKWAVE_FLASH = "application/x-shockwave-flash";
    public static final String MIME_APPLICATION_X_STUFFIT = "application/x-stuffit";
    public static final String MIME_APPLICATION_X_SV4CPIO = "application/x-sv4cpio";
    public static final String MIME_APPLICATION_X_SV4CRC = "application/x-sv4crc";
    public static final String MIME_APPLICATION_X_TAR = "application/x-tar";
    public static final String MIME_APPLICATION_X_RAR_COMPRESSED = "application/x-rar-compressed";
    public static final String MIME_APPLICATION_X_TCL = "application/x-tcl";
    public static final String MIME_APPLICATION_X_TEX = "application/x-tex";
    public static final String MIME_APPLICATION_X_TEXINFO = "application/x-texinfo";
    public static final String MIME_APPLICATION_X_TROFF = "application/x-troff";
    public static final String MIME_APPLICATION_X_TROFF_MAN = "application/x-troff-man";
    public static final String MIME_APPLICATION_X_TROFF_ME = "application/x-troff-me";
    public static final String MIME_APPLICATION_X_TROFF_MS = "application/x-troff-ms";
    public static final String MIME_APPLICATION_X_USTAR = "application/x-ustar";
    public static final String MIME_APPLICATION_X_WAIS_SOURCE = "application/x-wais-source";
    public static final String MIME_APPLICATION_VND_MOZZILLA_XUL_XML = "application/vnd.mozilla.xul+xml";
    public static final String MIME_APPLICATION_XHTML_XML = "application/xhtml+xml";
    public static final String MIME_APPLICATION_XSLT_XML = "application/xslt+xml";
    public static final String MIME_APPLICATION_XML = "application/xml";
    public static final String MIME_APPLICATION_RSS_XML = "application/rss+xml";
    public static final String MIME_APPLICATION_RSS_XML_UTF_8 = "application/rss+xml; charset=UTF-8";
    public static final String MIME_APPLICATION_TEXT_XML = "text/xml";
    public static final String MIME_APPLICATION_XML_DTD = "application/xml-dtd";
    public static final String MIME_IMAGE_BMP = "image/bmp";
    public static final String MIME_IMAGE_CGM = "image/cgm";
    public static final String MIME_IMAGE_GIF = "image/gif";
    public static final String MIME_IMAGE_IEF = "image/ief";
    public static final String MIME_IMAGE_JPEG = "image/jpeg";
    public static final String MIME_IMAGE_TIFF = "image/tiff";
    public static final String MIME_IMAGE_PNG = "image/png";
    public static final String MIME_IMAGE_SVG_XML = "image/svg+xml";
    public static final String MIME_IMAGE_VND_DJVU = "image/vnd.djvu";
    public static final String MIME_IMAGE_WAP_WBMP = "image/vnd.wap.wbmp";
    public static final String MIME_IMAGE_X_CMU_RASTER = "image/x-cmu-raster";
    public static final String MIME_IMAGE_X_ICON = "image/x-icon";
    public static final String MIME_IMAGE_X_PORTABLE_ANYMAP = "image/x-portable-anymap";
    public static final String MIME_IMAGE_X_PORTABLE_BITMAP = "image/x-portable-bitmap";
    public static final String MIME_IMAGE_X_PORTABLE_GRAYMAP = "image/x-portable-graymap";
    public static final String MIME_IMAGE_X_PORTABLE_PIXMAP = "image/x-portable-pixmap";
    public static final String MIME_IMAGE_X_RGB = "image/x-rgb";
    public static final String MIME_AUDIO_BASIC = "audio/basic";
    public static final String MIME_AUDIO_MIDI = "audio/midi";
    public static final String MIME_AUDIO_MPEG = "audio/mpeg";
    public static final String MIME_AUDIO_X_AIFF = "audio/x-aiff";
    public static final String MIME_AUDIO_X_MPEGURL = "audio/x-mpegurl";
    public static final String MIME_AUDIO_X_PN_REALAUDIO = "audio/x-pn-realaudio";
    public static final String MIME_AUDIO_X_WAV = "audio/x-wav";
    public static final String MIME_CHEMICAL_X_PDB = "chemical/x-pdb";
    public static final String MIME_CHEMICAL_X_XYZ = "chemical/x-xyz";
    public static final String MIME_MODEL_IGES = "model/iges";
    public static final String MIME_MODEL_MESH = "model/mesh";
    public static final String MIME_MODEL_VRLM = "model/vrml";
    public static final String MIME_TEXT_PLAIN = "text/plain";
    public static final String MIME_TEXT_PLAIN_UTF_8 = "text/plain;charset=utf-8";
    public static final String MIME_TEXT_RICHTEXT = "text/richtext";
    public static final String MIME_TEXT_RTF = "text/rtf";
    public static final String MIME_TEXT_HTML = "text/html";
    public static final String MIME_TEXT_HTML_UTF_8 = "text/html; charset=utf-8";
    public static final String MIME_TEXT_CALENDAR = "text/calendar";
    public static final String MIME_TEXT_CSS = "text/css";
    public static final String MIME_TEXT_SGML = "text/sgml";
    public static final String MIME_TEXT_TAB_SEPARATED_VALUES = "text/tab-separated-values";
    public static final String MIME_TEXT_VND_WAP_XML = "text/vnd.wap.wml";
    public static final String MIME_TEXT_VND_WAP_WMLSCRIPT = "text/vnd.wap.wmlscript";
    public static final String MIME_TEXT_X_SETEXT = "text/x-setext";
    public static final String MIME_TEXT_X_COMPONENT = "text/x-component";
    public static final String MIME_VIDEO_QUICKTIME = "video/quicktime";
    public static final String MIME_VIDEO_MPEG = "video/mpeg";
    public static final String MIME_VIDEO_VND_MPEGURL = "video/vnd.mpegurl";
    public static final String MIME_VIDEO_X_MSVIDEO = "video/x-msvideo";
    public static final String MIME_VIDEO_X_MS_WMV = "video/x-ms-wmv";
    public static final String MIME_VIDEO_X_SGI_MOVIE = "video/x-sgi-movie";
    public static final String MIME_X_CONFERENCE_X_COOLTALK = "x-conference/x-cooltalk";

    private static final Map<String, String> MIME_TYPE_MAPPING;
    private static final Map<String, String> EXT_MAPPING;

    static {
        MIME_TYPE_MAPPING = new HashMap<>(200);
        MIME_TYPE_MAPPING.put("xul", MIME_APPLICATION_VND_MOZZILLA_XUL_XML);
        MIME_TYPE_MAPPING.put("json", MIME_APPLICATION_JSON);
        MIME_TYPE_MAPPING.put("ice", MIME_X_CONFERENCE_X_COOLTALK);
        MIME_TYPE_MAPPING.put("movie", MIME_VIDEO_X_SGI_MOVIE);
        MIME_TYPE_MAPPING.put("avi", MIME_VIDEO_X_MSVIDEO);
        MIME_TYPE_MAPPING.put("wmv", MIME_VIDEO_X_MS_WMV);
        MIME_TYPE_MAPPING.put("m4u", MIME_VIDEO_VND_MPEGURL);
        MIME_TYPE_MAPPING.put("mxu", MIME_VIDEO_VND_MPEGURL);
        MIME_TYPE_MAPPING.put("htc", MIME_TEXT_X_COMPONENT);
        MIME_TYPE_MAPPING.put("etx", MIME_TEXT_X_SETEXT);
        MIME_TYPE_MAPPING.put("wmls", MIME_TEXT_VND_WAP_WMLSCRIPT);
        MIME_TYPE_MAPPING.put("wml", MIME_TEXT_VND_WAP_XML);
        MIME_TYPE_MAPPING.put("tsv", MIME_TEXT_TAB_SEPARATED_VALUES);
        MIME_TYPE_MAPPING.put("sgm", MIME_TEXT_SGML);
        MIME_TYPE_MAPPING.put("sgml", MIME_TEXT_SGML);
        MIME_TYPE_MAPPING.put("css", MIME_TEXT_CSS);
        MIME_TYPE_MAPPING.put("ifb", MIME_TEXT_CALENDAR);
        MIME_TYPE_MAPPING.put("ics", MIME_TEXT_CALENDAR);
        MIME_TYPE_MAPPING.put("wrl", MIME_MODEL_VRLM);
        MIME_TYPE_MAPPING.put("vrlm", MIME_MODEL_VRLM);
        MIME_TYPE_MAPPING.put("silo", MIME_MODEL_MESH);
        MIME_TYPE_MAPPING.put("mesh", MIME_MODEL_MESH);
        MIME_TYPE_MAPPING.put("msh", MIME_MODEL_MESH);
        MIME_TYPE_MAPPING.put("iges", MIME_MODEL_IGES);
        MIME_TYPE_MAPPING.put("igs", MIME_MODEL_IGES);
        MIME_TYPE_MAPPING.put("rgb", MIME_IMAGE_X_RGB);
        MIME_TYPE_MAPPING.put("ppm", MIME_IMAGE_X_PORTABLE_PIXMAP);
        MIME_TYPE_MAPPING.put("pgm", MIME_IMAGE_X_PORTABLE_GRAYMAP);
        MIME_TYPE_MAPPING.put("pbm", MIME_IMAGE_X_PORTABLE_BITMAP);
        MIME_TYPE_MAPPING.put("pnm", MIME_IMAGE_X_PORTABLE_ANYMAP);
        MIME_TYPE_MAPPING.put("ico", MIME_IMAGE_X_ICON);
        MIME_TYPE_MAPPING.put("ras", MIME_IMAGE_X_CMU_RASTER);
        MIME_TYPE_MAPPING.put("wbmp", MIME_IMAGE_WAP_WBMP);
        MIME_TYPE_MAPPING.put("djv", MIME_IMAGE_VND_DJVU);
        MIME_TYPE_MAPPING.put("djvu", MIME_IMAGE_VND_DJVU);
        MIME_TYPE_MAPPING.put("svg", MIME_IMAGE_SVG_XML);
        MIME_TYPE_MAPPING.put("ief", MIME_IMAGE_IEF);
        MIME_TYPE_MAPPING.put("cgm", MIME_IMAGE_CGM);
        MIME_TYPE_MAPPING.put("bmp", MIME_IMAGE_BMP);
        MIME_TYPE_MAPPING.put("xyz", MIME_CHEMICAL_X_XYZ);
        MIME_TYPE_MAPPING.put("pdb", MIME_CHEMICAL_X_PDB);
        MIME_TYPE_MAPPING.put("ra", MIME_AUDIO_X_PN_REALAUDIO);
        MIME_TYPE_MAPPING.put("ram", MIME_AUDIO_X_PN_REALAUDIO);
        MIME_TYPE_MAPPING.put("m3u", MIME_AUDIO_X_MPEGURL);
        MIME_TYPE_MAPPING.put("aifc", MIME_AUDIO_X_AIFF);
        MIME_TYPE_MAPPING.put("aif", MIME_AUDIO_X_AIFF);
        MIME_TYPE_MAPPING.put("aiff", MIME_AUDIO_X_AIFF);
        MIME_TYPE_MAPPING.put("mp3", MIME_AUDIO_MPEG);
        MIME_TYPE_MAPPING.put("mp2", MIME_AUDIO_MPEG);
        MIME_TYPE_MAPPING.put("mp1", MIME_AUDIO_MPEG);
        MIME_TYPE_MAPPING.put("mpga", MIME_AUDIO_MPEG);
        MIME_TYPE_MAPPING.put("kar", MIME_AUDIO_MIDI);
        MIME_TYPE_MAPPING.put("mid", MIME_AUDIO_MIDI);
        MIME_TYPE_MAPPING.put("midi", MIME_AUDIO_MIDI);
        MIME_TYPE_MAPPING.put("dtd", MIME_APPLICATION_XML_DTD);
        MIME_TYPE_MAPPING.put("xsl", MIME_APPLICATION_XML);
        MIME_TYPE_MAPPING.put("xml", MIME_APPLICATION_XML);
        MIME_TYPE_MAPPING.put("xslt", MIME_APPLICATION_XSLT_XML);
        MIME_TYPE_MAPPING.put("xht", MIME_APPLICATION_XHTML_XML);
        MIME_TYPE_MAPPING.put("xhtml", MIME_APPLICATION_XHTML_XML);
        MIME_TYPE_MAPPING.put("src", MIME_APPLICATION_X_WAIS_SOURCE);
        MIME_TYPE_MAPPING.put("ustar", MIME_APPLICATION_X_USTAR);
        MIME_TYPE_MAPPING.put("ms", MIME_APPLICATION_X_TROFF_MS);
        MIME_TYPE_MAPPING.put("me", MIME_APPLICATION_X_TROFF_ME);
        MIME_TYPE_MAPPING.put("man", MIME_APPLICATION_X_TROFF_MAN);
        MIME_TYPE_MAPPING.put("roff", MIME_APPLICATION_X_TROFF);
        MIME_TYPE_MAPPING.put("tr", MIME_APPLICATION_X_TROFF);
        MIME_TYPE_MAPPING.put("t", MIME_APPLICATION_X_TROFF);
        MIME_TYPE_MAPPING.put("texi", MIME_APPLICATION_X_TEXINFO);
        MIME_TYPE_MAPPING.put("texinfo", MIME_APPLICATION_X_TEXINFO);
        MIME_TYPE_MAPPING.put("tex", MIME_APPLICATION_X_TEX);
        MIME_TYPE_MAPPING.put("tcl", MIME_APPLICATION_X_TCL);
        MIME_TYPE_MAPPING.put("sv4crc", MIME_APPLICATION_X_SV4CRC);
        MIME_TYPE_MAPPING.put("sv4cpio", MIME_APPLICATION_X_SV4CPIO);
        MIME_TYPE_MAPPING.put("sit", MIME_APPLICATION_X_STUFFIT);
        MIME_TYPE_MAPPING.put("swf", MIME_APPLICATION_X_SHOCKWAVE_FLASH);
        MIME_TYPE_MAPPING.put("shar", MIME_APPLICATION_X_SHAR);
        MIME_TYPE_MAPPING.put("sh", MIME_APPLICATION_X_SH);
        MIME_TYPE_MAPPING.put("cdf", MIME_APPLICATION_X_NETCDF);
        MIME_TYPE_MAPPING.put("nc", MIME_APPLICATION_X_NETCDF);
        MIME_TYPE_MAPPING.put("latex", MIME_APPLICATION_X_LATEX);
        MIME_TYPE_MAPPING.put("skm", MIME_APPLICATION_X_KOAN);
        MIME_TYPE_MAPPING.put("skt", MIME_APPLICATION_X_KOAN);
        MIME_TYPE_MAPPING.put("skd", MIME_APPLICATION_X_KOAN);
        MIME_TYPE_MAPPING.put("skp", MIME_APPLICATION_X_KOAN);
        MIME_TYPE_MAPPING.put("js", MIME_APPLICATION_X_JAVASCRIPT);
        MIME_TYPE_MAPPING.put("hdf", MIME_APPLICATION_X_HDF);
        MIME_TYPE_MAPPING.put("gtar", MIME_APPLICATION_X_GTAR);
        MIME_TYPE_MAPPING.put("spl", MIME_APPLICATION_X_FUTURESPLASH);
        MIME_TYPE_MAPPING.put("dvi", MIME_APPLICATION_X_DVI);
        MIME_TYPE_MAPPING.put("dxr", MIME_APPLICATION_X_DIRECTOR);
        MIME_TYPE_MAPPING.put("dir", MIME_APPLICATION_X_DIRECTOR);
        MIME_TYPE_MAPPING.put("dcr", MIME_APPLICATION_X_DIRECTOR);
        MIME_TYPE_MAPPING.put("csh", MIME_APPLICATION_X_CSH);
        MIME_TYPE_MAPPING.put("cpio", MIME_APPLICATION_X_CPIO);
        MIME_TYPE_MAPPING.put("pgn", MIME_APPLICATION_X_CHESS_PGN);
        MIME_TYPE_MAPPING.put("vcd", MIME_APPLICATION_X_CDLINK);
        MIME_TYPE_MAPPING.put("bcpio", MIME_APPLICATION_X_BCPIO);
        MIME_TYPE_MAPPING.put("rm", MIME_APPLICATION_VND_RNREALMEDIA);
        MIME_TYPE_MAPPING.put("ppt", MIME_APPLICATION_VND_MSPOWERPOINT);
        MIME_TYPE_MAPPING.put("mif", MIME_APPLICATION_VND_MIF);
        MIME_TYPE_MAPPING.put("grxml", MIME_APPLICATION_SRGS_XML);
        MIME_TYPE_MAPPING.put("gram", MIME_APPLICATION_SRGS);
        MIME_TYPE_MAPPING.put("smil", MIME_APPLICATION_RDF_SMIL);
        MIME_TYPE_MAPPING.put("smi", MIME_APPLICATION_RDF_SMIL);
        MIME_TYPE_MAPPING.put("rdf", MIME_APPLICATION_RDF_XML);
        MIME_TYPE_MAPPING.put("ogg", MIME_APPLICATION_X_OGG);
        MIME_TYPE_MAPPING.put("oda", MIME_APPLICATION_ODA);
        MIME_TYPE_MAPPING.put("dmg", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("lzh", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("so", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("lha", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("dms", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("bin", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("mathml", MIME_APPLICATION_MATHML_XML);
        MIME_TYPE_MAPPING.put("cpt", MIME_APPLICATION_MAC_COMPACTPRO);
        MIME_TYPE_MAPPING.put("hqx", MIME_APPLICATION_MAC_BINHEX40);
        MIME_TYPE_MAPPING.put("jnlp", MIME_APPLICATION_JNLP);
        MIME_TYPE_MAPPING.put("ez", MIME_APPLICATION_ANDREW_INSET);
        MIME_TYPE_MAPPING.put("txt", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("ini", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("c", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("h", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("cpp", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("cxx", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("cc", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("chh", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("java", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("csv", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("bat", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("cmd", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("asc", MIME_TEXT_PLAIN);
        MIME_TYPE_MAPPING.put("rtf", MIME_TEXT_RTF);
        MIME_TYPE_MAPPING.put("rtx", MIME_TEXT_RICHTEXT);
        MIME_TYPE_MAPPING.put("html", MIME_TEXT_HTML);
        MIME_TYPE_MAPPING.put("htm", MIME_TEXT_HTML);
        MIME_TYPE_MAPPING.put("zip", MIME_APPLICATION_ZIP);
        MIME_TYPE_MAPPING.put("rar", MIME_APPLICATION_X_RAR_COMPRESSED);
        MIME_TYPE_MAPPING.put("gzip", MIME_APPLICATION_X_GZIP);
        MIME_TYPE_MAPPING.put("gz", MIME_APPLICATION_X_GZIP);
        MIME_TYPE_MAPPING.put("tgz", MIME_APPLICATION_TGZ);
        MIME_TYPE_MAPPING.put("tar", MIME_APPLICATION_X_TAR);
        MIME_TYPE_MAPPING.put("gif", MIME_IMAGE_GIF);
        MIME_TYPE_MAPPING.put("jpeg", MIME_IMAGE_JPEG);
        MIME_TYPE_MAPPING.put("jpg", MIME_IMAGE_JPEG);
        MIME_TYPE_MAPPING.put("jpe", MIME_IMAGE_JPEG);
        MIME_TYPE_MAPPING.put("tiff", MIME_IMAGE_TIFF);
        MIME_TYPE_MAPPING.put("tif", MIME_IMAGE_TIFF);
        MIME_TYPE_MAPPING.put("png", MIME_IMAGE_PNG);
        MIME_TYPE_MAPPING.put("au", MIME_AUDIO_BASIC);
        MIME_TYPE_MAPPING.put("snd", MIME_AUDIO_BASIC);
        MIME_TYPE_MAPPING.put("wav", MIME_AUDIO_X_WAV);
        MIME_TYPE_MAPPING.put("mov", MIME_VIDEO_QUICKTIME);
        MIME_TYPE_MAPPING.put("qt", MIME_VIDEO_QUICKTIME);
        MIME_TYPE_MAPPING.put("mpeg", MIME_VIDEO_MPEG);
        MIME_TYPE_MAPPING.put("mpg", MIME_VIDEO_MPEG);
        MIME_TYPE_MAPPING.put("mpe", MIME_VIDEO_MPEG);
        MIME_TYPE_MAPPING.put("abs", MIME_VIDEO_MPEG);
        MIME_TYPE_MAPPING.put("doc", MIME_APPLICATION_MSWORD);
        MIME_TYPE_MAPPING.put("docx", MIME_APPLICATION_MSWORD_2007);
        MIME_TYPE_MAPPING.put("odt", MIME_APPLICATION_VND_TEXT);
        MIME_TYPE_MAPPING.put("xls", MIME_APPLICATION_VND_MSEXCEL);
        MIME_TYPE_MAPPING.put("xlsx", MIME_APPLICATION_VND_MSEXCEL_2007);
        MIME_TYPE_MAPPING.put("ods", MIME_APPLICATION_VND_SPREADSHEET);
        MIME_TYPE_MAPPING.put("eps", MIME_APPLICATION_POSTSCRIPT);
        MIME_TYPE_MAPPING.put("ai", MIME_APPLICATION_POSTSCRIPT);
        MIME_TYPE_MAPPING.put("ps", MIME_APPLICATION_POSTSCRIPT);
        MIME_TYPE_MAPPING.put("pdf", MIME_APPLICATION_PDF);
        MIME_TYPE_MAPPING.put("exe", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("dll", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("class", MIME_APPLICATION_OCTET_STREAM);
        MIME_TYPE_MAPPING.put("jar", MIME_APPLICATION_JAVA_ARCHIVE);
    }

    static {
        EXT_MAPPING = new HashMap<>(200);
        EXT_MAPPING.put(MIME_APPLICATION_VND_MOZZILLA_XUL_XML, "xul");
        EXT_MAPPING.put(MIME_APPLICATION_JSON, "json");
        EXT_MAPPING.put(MIME_X_CONFERENCE_X_COOLTALK, "ice");
        EXT_MAPPING.put(MIME_VIDEO_X_SGI_MOVIE, "movie");
        EXT_MAPPING.put(MIME_VIDEO_X_MSVIDEO, "avi");
        EXT_MAPPING.put(MIME_VIDEO_X_MS_WMV, "wmv");
        EXT_MAPPING.put(MIME_VIDEO_VND_MPEGURL, "m4u");
        EXT_MAPPING.put(MIME_TEXT_X_COMPONENT, "htc");
        EXT_MAPPING.put(MIME_TEXT_X_SETEXT, "etx");
        EXT_MAPPING.put(MIME_TEXT_VND_WAP_WMLSCRIPT, "wmls");
        EXT_MAPPING.put(MIME_TEXT_VND_WAP_XML, "wml");
        EXT_MAPPING.put(MIME_TEXT_TAB_SEPARATED_VALUES, "tsv");
        EXT_MAPPING.put(MIME_TEXT_SGML, "sgml");
        EXT_MAPPING.put(MIME_TEXT_CSS, "css");
        EXT_MAPPING.put(MIME_TEXT_CALENDAR, "ics");
        EXT_MAPPING.put(MIME_MODEL_VRLM, "vrlm");
        EXT_MAPPING.put(MIME_MODEL_MESH, "mesh");
        EXT_MAPPING.put(MIME_MODEL_IGES, "iges");
        EXT_MAPPING.put(MIME_IMAGE_X_RGB, "rgb");
        EXT_MAPPING.put(MIME_IMAGE_X_PORTABLE_PIXMAP, "ppm");
        EXT_MAPPING.put(MIME_IMAGE_X_PORTABLE_GRAYMAP, "pgm");
        EXT_MAPPING.put(MIME_IMAGE_X_PORTABLE_BITMAP, "pbm");
        EXT_MAPPING.put(MIME_IMAGE_X_PORTABLE_ANYMAP, "pnm");
        EXT_MAPPING.put(MIME_IMAGE_X_ICON, "ico");
        EXT_MAPPING.put(MIME_IMAGE_X_CMU_RASTER, "ras");
        EXT_MAPPING.put(MIME_IMAGE_WAP_WBMP, "wbmp");
        EXT_MAPPING.put(MIME_IMAGE_VND_DJVU, "djvu");
        EXT_MAPPING.put(MIME_IMAGE_SVG_XML, "svg");
        EXT_MAPPING.put(MIME_IMAGE_IEF, "ief");
        EXT_MAPPING.put(MIME_IMAGE_CGM, "cgm");
        EXT_MAPPING.put(MIME_IMAGE_BMP, "bmp");
        EXT_MAPPING.put(MIME_CHEMICAL_X_XYZ, "xyz");
        EXT_MAPPING.put(MIME_CHEMICAL_X_PDB, "pdb");
        EXT_MAPPING.put(MIME_AUDIO_X_PN_REALAUDIO, "ra");
        EXT_MAPPING.put(MIME_AUDIO_X_MPEGURL, "m3u");
        EXT_MAPPING.put(MIME_AUDIO_X_AIFF, "aiff");
        EXT_MAPPING.put(MIME_AUDIO_MPEG, "mp3");
        EXT_MAPPING.put(MIME_AUDIO_MIDI, "midi");
        EXT_MAPPING.put(MIME_APPLICATION_XML_DTD, "dtd");
        EXT_MAPPING.put(MIME_APPLICATION_XML, "xml");
        EXT_MAPPING.put(MIME_APPLICATION_TEXT_XML, "xml");
        EXT_MAPPING.put(MIME_APPLICATION_RSS_XML, "xml");
        EXT_MAPPING.put(MIME_APPLICATION_RSS_XML_UTF_8, "xml");
        EXT_MAPPING.put(MIME_APPLICATION_XSLT_XML, "xslt");
        EXT_MAPPING.put(MIME_APPLICATION_XHTML_XML, "xhtml");
        EXT_MAPPING.put(MIME_APPLICATION_X_WAIS_SOURCE, "src");
        EXT_MAPPING.put(MIME_APPLICATION_X_USTAR, "ustar");
        EXT_MAPPING.put(MIME_APPLICATION_X_TROFF_MS, "ms");
        EXT_MAPPING.put(MIME_APPLICATION_X_TROFF_ME, "me");
        EXT_MAPPING.put(MIME_APPLICATION_X_TROFF_MAN, "man");
        EXT_MAPPING.put(MIME_APPLICATION_X_TROFF, "roff");
        EXT_MAPPING.put(MIME_APPLICATION_X_TEXINFO, "texi");
        EXT_MAPPING.put(MIME_APPLICATION_X_TEX, "tex");
        EXT_MAPPING.put(MIME_APPLICATION_X_TCL, "tcl");
        EXT_MAPPING.put(MIME_APPLICATION_X_SV4CRC, "sv4crc");
        EXT_MAPPING.put(MIME_APPLICATION_X_SV4CPIO, "sv4cpio");
        EXT_MAPPING.put(MIME_APPLICATION_X_STUFFIT, "sit");
        EXT_MAPPING.put(MIME_APPLICATION_X_SHOCKWAVE_FLASH, "swf");
        EXT_MAPPING.put(MIME_APPLICATION_X_SHAR, "shar");
        EXT_MAPPING.put(MIME_APPLICATION_X_SH, "sh");
        EXT_MAPPING.put(MIME_APPLICATION_X_NETCDF, "cdf");
        EXT_MAPPING.put(MIME_APPLICATION_X_LATEX, "latex");
        EXT_MAPPING.put(MIME_APPLICATION_X_KOAN, "skm");
        EXT_MAPPING.put(MIME_APPLICATION_X_JAVASCRIPT, "js");
        EXT_MAPPING.put(MIME_APPLICATION_X_HDF, "hdf");
        EXT_MAPPING.put(MIME_APPLICATION_X_GTAR, "gtar");
        EXT_MAPPING.put(MIME_APPLICATION_X_FUTURESPLASH, "spl");
        EXT_MAPPING.put(MIME_APPLICATION_X_DVI, "dvi");
        EXT_MAPPING.put(MIME_APPLICATION_X_DIRECTOR, "dir");
        EXT_MAPPING.put(MIME_APPLICATION_X_CSH, "csh");
        EXT_MAPPING.put(MIME_APPLICATION_X_CPIO, "cpio");
        EXT_MAPPING.put(MIME_APPLICATION_X_CHESS_PGN, "pgn");
        EXT_MAPPING.put(MIME_APPLICATION_X_CDLINK, "vcd");
        EXT_MAPPING.put(MIME_APPLICATION_X_BCPIO, "bcpio");
        EXT_MAPPING.put(MIME_APPLICATION_VND_RNREALMEDIA, "rm");
        EXT_MAPPING.put(MIME_APPLICATION_VND_MSPOWERPOINT, "ppt");
        EXT_MAPPING.put(MIME_APPLICATION_VND_MIF, "mif");
        EXT_MAPPING.put(MIME_APPLICATION_SRGS_XML, "grxml");
        EXT_MAPPING.put(MIME_APPLICATION_SRGS, "gram");
        EXT_MAPPING.put(MIME_APPLICATION_RDF_SMIL, "smil");
        EXT_MAPPING.put(MIME_APPLICATION_RDF_XML, "rdf");
        EXT_MAPPING.put(MIME_APPLICATION_X_OGG, "ogg");
        EXT_MAPPING.put(MIME_APPLICATION_ODA, "oda");
        EXT_MAPPING.put(MIME_APPLICATION_MATHML_XML, "mathml");
        EXT_MAPPING.put(MIME_APPLICATION_MAC_COMPACTPRO, "cpt");
        EXT_MAPPING.put(MIME_APPLICATION_MAC_BINHEX40, "hqx");
        EXT_MAPPING.put(MIME_APPLICATION_JNLP, "jnlp");
        EXT_MAPPING.put(MIME_APPLICATION_ANDREW_INSET, "ez");
        EXT_MAPPING.put(MIME_TEXT_PLAIN, "txt");
        EXT_MAPPING.put(MIME_TEXT_PLAIN_UTF_8, "txt");
        EXT_MAPPING.put(MIME_TEXT_RTF, "rtf");
        EXT_MAPPING.put(MIME_TEXT_RICHTEXT, "rtx");
        EXT_MAPPING.put(MIME_TEXT_HTML, "html");
        EXT_MAPPING.put(MIME_TEXT_HTML_UTF_8, "html");
        EXT_MAPPING.put(MIME_APPLICATION_ZIP, "zip");
        EXT_MAPPING.put(MIME_APPLICATION_X_RAR_COMPRESSED, "rar");
        EXT_MAPPING.put(MIME_APPLICATION_X_GZIP, "gzip");
        EXT_MAPPING.put(MIME_APPLICATION_TGZ, "tgz");
        EXT_MAPPING.put(MIME_APPLICATION_X_TAR, "tar");
        EXT_MAPPING.put(MIME_IMAGE_GIF, "gif");
        EXT_MAPPING.put(MIME_IMAGE_JPEG, "jpg");
        EXT_MAPPING.put(MIME_IMAGE_TIFF, "tiff");
        EXT_MAPPING.put(MIME_IMAGE_PNG, "png");
        EXT_MAPPING.put(MIME_AUDIO_BASIC, "au");
        EXT_MAPPING.put(MIME_AUDIO_X_WAV, "wav");
        EXT_MAPPING.put(MIME_VIDEO_QUICKTIME, "mov");
        EXT_MAPPING.put(MIME_VIDEO_MPEG, "mpg");
        EXT_MAPPING.put(MIME_APPLICATION_MSWORD, "doc");
        EXT_MAPPING.put(MIME_APPLICATION_MSWORD_2007, "docx");
        EXT_MAPPING.put(MIME_APPLICATION_VND_TEXT, "odt");
        EXT_MAPPING.put(MIME_APPLICATION_VND_MSEXCEL, "xls");
        EXT_MAPPING.put(MIME_APPLICATION_VND_SPREADSHEET, "ods");
        EXT_MAPPING.put(MIME_APPLICATION_POSTSCRIPT, "ps");
        EXT_MAPPING.put(MIME_APPLICATION_PDF, "pdf");
        EXT_MAPPING.put(MIME_APPLICATION_OCTET_STREAM, "exe");
        EXT_MAPPING.put(MIME_APPLICATION_JAVA_ARCHIVE, "jar");
    }

    /**
     * Registers MIME type for provided extension. Existing extension type will be overridden.
     */
    public static void registerMimeType(String ext, String mimeType) {
        MIME_TYPE_MAPPING.put(ext, mimeType);
    }

    /**
     * Returns the corresponding MIME type to the given extension.
     * If no MIME type was found it returns 'application/octet-stream' type.
     */
    public static String getMimeType(String ext) {
        String mimeType = lookupMimeType(ext);
        if (mimeType == null) {
            mimeType = MIME_APPLICATION_OCTET_STREAM;
        }
        return mimeType;
    }

    /**
     * Simply returns MIME type or <code>null</code> if no type is found.
     */
    public static String lookupMimeType(String ext) {
        return MIME_TYPE_MAPPING.get(ext.toLowerCase());
    }

    /**
     * Simply returns Ext or <code>null</code> if no Mimetype is found.
     */
    public static String lookupExt(String mimeType) {
        return EXT_MAPPING.get(mimeType.toLowerCase());
    }

    /**
     * Returns the default Ext to the given MimeType.
     * If no MIME type was found it returns 'unknown' ext.
     */
    public static String getDefaultExt(String mimeType) {
        String ext = lookupExt(mimeType);
        if (ext == null) {
            ext = "unknown";
        }
        return ext;
    }

    public static String getDefaultExtWithDot(String mimeType) {
        return '.' + getDefaultExt(mimeType);
    }
}
