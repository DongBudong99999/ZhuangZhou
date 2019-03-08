//package springboot_demo1.demo.tools;
//
//import org.geotools.data.shapefile.ShapefileDataStore;
//import org.geotools.data.simple.SimpleFeatureCollection;
//import org.geotools.data.simple.SimpleFeatureIterator;
//import org.geotools.data.simple.SimpleFeatureSource;
//import org.geotools.geojson.feature.FeatureJSON;
//import org.json.simple.JSONArray;
//import org.opengis.feature.simple.SimpleFeature;
//
//import java.io.*;
//import java.nio.charset.Charset;
//
///**
// * Created by kay on 2018/2/1.
// */
//public class gjsDemo {
//
//    public static void shp2Json(String shpPath) {
//        StringBuffer sb = new StringBuffer();
//        FeatureJSON fjson = new FeatureJSON();
//
//        try {
//            sb.append("{\"type\": \"FeatureCollection\",\"features\": ");
//
//            File file = new File(shpPath);
//            ShapefileDataStore shpDataStore = null;
//            System.out.println("```");
//
//            shpDataStore = new ShapefileDataStore(file.toURL());
//            //设置编码
//            Charset charset = Charset.forName("UTF-8");
//            shpDataStore.setCharset(charset);
//            String typeName = shpDataStore.getTypeNames()[0];
//            SimpleFeatureSource featureSource = null;
//            featureSource = shpDataStore.getFeatureSource(typeName);
//            SimpleFeatureCollection result = featureSource.getFeatures();
//            SimpleFeatureIterator itertor = result.features();
//            JSONArray array = new JSONArray();
//            while (itertor.hasNext()) {
//                SimpleFeature feature = itertor.next();
//                StringWriter writer = new StringWriter();
//                fjson.writeFeature(feature, writer);
//                array.add(writer);
//            }
//            itertor.close();
//            sb.append(array.toString());
//            sb.append("}");
//            //写到文件
//            writeToFile("/Users/dongbudong/Desktop/beijing.geojson", sb.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args) {
//        shp2Json("/Users/dongbudong/Desktop/mjg15lb.mjg15lb-1/mjg15lb.mjg15lb-1");
//    }
//
//    /**
//     * 写出到文件
//     *
//     * @param targetFile 目标文件路径
//     * @param soure      json
//     */
//    public static void writeToFile(String targetFile, String soure) {
//        File file = new File(targetFile);
//
//        try {
//            OutputStream os = new FileOutputStream(file);
//            OutputStreamWriter osw = new OutputStreamWriter(os);
//            osw.write(soure);
//            osw.flush();
//            osw.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
