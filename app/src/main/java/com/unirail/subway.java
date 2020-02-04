package com.unirail;

import java.util.ArrayList;
import java.util.HashMap;

public class subway
{
    public static final HashMap<String, String> statnNms=new HashMap<String, String>();
    public static final HashMap<String, String> subwayIds=new HashMap<String, String>();
    public static final HashMap<String,line> lines=new HashMap<String,line>();

    public static final void initialize()
    {
        initialize_statnNms();
        initialize_subwayIds();
        initialize_lines();
    }

    private static final void initialize_statnNms()
    {
        statnNms.put("0150","서울");
        statnNms.put("0151","시청");
        statnNms.put("0152","종각");
        statnNms.put("0153","종로3가");
        statnNms.put("0154","종로5가");
        statnNms.put("0155","동대문");
        statnNms.put("0156","신설동");
        statnNms.put("0157","제기동");
        statnNms.put("0158","청량리");
        statnNms.put("0159","동묘앞");
        statnNms.put("0201","시청");
        statnNms.put("0202","을지로입구");
        statnNms.put("0203","을지로3가");
        statnNms.put("0204","을지로4가");
        statnNms.put("0205","동대문역사문화공원");
        statnNms.put("0206","신당");
        statnNms.put("0207","상왕십리");
        statnNms.put("0208","왕십리");
        statnNms.put("0209","한양대");
        statnNms.put("0210","뚝섬");
        statnNms.put("0211","성수");
        statnNms.put("0212","건대입구");
        statnNms.put("0213","구의");
        statnNms.put("0214","강변");
        statnNms.put("0215","잠실나루");
        statnNms.put("0216","잠실");
        statnNms.put("0217","잠실새내");
        statnNms.put("0218","종합운동장");
        statnNms.put("0219","삼성");
        statnNms.put("0220","선릉");
        statnNms.put("0221","역삼");
        statnNms.put("0222","강남");
        statnNms.put("0223","교대");
        statnNms.put("0224","서초");
        statnNms.put("0225","방배");
        statnNms.put("0226","사당");
        statnNms.put("0227","낙성대");
        statnNms.put("0228","서울대입구");
        statnNms.put("0229","봉천");
        statnNms.put("0230","신림");
        statnNms.put("0231","신대방");
        statnNms.put("0232","구로디지털단지");
        statnNms.put("0233","대림");
        statnNms.put("0234","신도림");
        statnNms.put("0235","문래");
        statnNms.put("0236","영등포구청");
        statnNms.put("0237","당산");
        statnNms.put("0238","합정");
        statnNms.put("0239","홍대입구");
        statnNms.put("0240","신촌");
        statnNms.put("0241","이대");
        statnNms.put("0242","아현");
        statnNms.put("0243","충정로");
        statnNms.put("0244","용답");
        statnNms.put("0245","신답");
        statnNms.put("0246","신설동");
        statnNms.put("0247","도림천");
        statnNms.put("0248","양천구청");
        statnNms.put("0249","신정네거리");
        statnNms.put("0250","용두");
        statnNms.put("0260","까치산");
        statnNms.put("0309","지축");
        statnNms.put("0310","구파발");
        statnNms.put("0311","연신내");
        statnNms.put("0312","불광");
        statnNms.put("0313","녹번");
        statnNms.put("0314","홍제");
        statnNms.put("0315","무악재");
        statnNms.put("0316","독립문");
        statnNms.put("0317","경복궁");
        statnNms.put("0318","안국");
        statnNms.put("0319","종로3가");
        statnNms.put("0320","을지로3가");
        statnNms.put("0321","충무로");
        statnNms.put("0322","동대입구");
        statnNms.put("0323","약수");
        statnNms.put("0324","금호");
        statnNms.put("0325","옥수");
        statnNms.put("0326","압구정");
        statnNms.put("0327","새절(신사)");
        statnNms.put("0328","잠원");
        statnNms.put("0329","고속터미널");
        statnNms.put("0330","교대");
        statnNms.put("0331","남부터미널");
        statnNms.put("0332","양재");
        statnNms.put("0333","매봉");
        statnNms.put("0334","도곡");
        statnNms.put("0335","대치");
        statnNms.put("0336","학여울");
        statnNms.put("0337","대청");
        statnNms.put("0338","일원");
        statnNms.put("0339","수서");
        statnNms.put("0340","가락시장");
        statnNms.put("0341","경찰병원");
        statnNms.put("0342","오금");
        statnNms.put("0409","당고개");
        statnNms.put("0410","상계");
        statnNms.put("0411","노원");
        statnNms.put("0412","창동");
        statnNms.put("0413","쌍문");
        statnNms.put("0414","수유");
        statnNms.put("0415","미아");
        statnNms.put("0416","미아사거리");
        statnNms.put("0417","길음");
        statnNms.put("0418","성신여대입구");
        statnNms.put("0419","한성대입구");
        statnNms.put("0420","혜화");
        statnNms.put("0421","동대문");
        statnNms.put("0422","동대문역사문화공원");
        statnNms.put("0423","충무로");
        statnNms.put("0424","명동");
        statnNms.put("0425","회현");
        statnNms.put("0426","서울");
        statnNms.put("0427","숙대입구");
        statnNms.put("0428","삼각지");
        statnNms.put("0429","신용산");
        statnNms.put("0430","이촌");
        statnNms.put("0431","동작");
        statnNms.put("0432","총신대입구(이수)");
        statnNms.put("0433","사당");
        statnNms.put("0434","남태령");
        statnNms.put("1002","남영");
        statnNms.put("1003","용산");
        statnNms.put("1004","노량진");
        statnNms.put("1005","대방");
        statnNms.put("1006","영등포");
        statnNms.put("1007","신도림");
        statnNms.put("1008","이촌");
        statnNms.put("1009","서빙고");
        statnNms.put("1010","한남");
        statnNms.put("1011","옥수");
        statnNms.put("1012","응봉");
        statnNms.put("1013","왕십리");
        statnNms.put("1014","청량리");
        statnNms.put("1015","회기");
        statnNms.put("1016","외대앞");
        statnNms.put("1017","신이문");
        statnNms.put("1018","석계");
        statnNms.put("1019","광운대");
        statnNms.put("1020","월계");
        statnNms.put("1021","녹천");
        statnNms.put("1022","창동");
        statnNms.put("1023","선릉");
        statnNms.put("1024","한티");
        statnNms.put("1025","도곡");
        statnNms.put("1026","구룡");
        statnNms.put("1027","개포동");
        statnNms.put("1028","대모산입구");
        statnNms.put("1030","수서");
        statnNms.put("1031","복정");
        statnNms.put("1032","신길");
        statnNms.put("1200","회기");
        statnNms.put("1201","중랑");
        statnNms.put("1202","상봉");
        statnNms.put("1203","망우");
        statnNms.put("1204","양원");
        statnNms.put("1205","구리");
        statnNms.put("1206","도농");
        statnNms.put("1207","양정");
        statnNms.put("1208","덕소");
        statnNms.put("1209","도심");
        statnNms.put("1210","팔당");
        statnNms.put("1211","운길산");
        statnNms.put("1212","양수");
        statnNms.put("1213","신원");
        statnNms.put("1214","국수");
        statnNms.put("1215","아신");
        statnNms.put("1216","오빈");
        statnNms.put("1217","양평");
        statnNms.put("1218","원덕");
        statnNms.put("1219","용문");
        statnNms.put("1220","지평");
        statnNms.put("1249","대곡");
        statnNms.put("1251","서울");
        statnNms.put("1252","신촌(경의.중앙선)");
        statnNms.put("1261","효창공원앞");
        statnNms.put("1262","공덕");
        statnNms.put("1263","서강대");
        statnNms.put("1264","홍대입구");
        statnNms.put("1265","가좌");
        statnNms.put("1266","디지털미디어시티");
        statnNms.put("1267","수색");
        statnNms.put("1268","화전");
        statnNms.put("1269","강매");
        statnNms.put("1270","행신");
        statnNms.put("1271","능곡");
        statnNms.put("1272","곡산");
        statnNms.put("1273","백마");
        statnNms.put("1274","풍산");
        statnNms.put("1275","일산");
        statnNms.put("1276","탄현");
        statnNms.put("1277","야당");
        statnNms.put("1278","운정");
        statnNms.put("1279","금릉");
        statnNms.put("1280","금촌");
        statnNms.put("1282","월롱");
        statnNms.put("1283","파주");
        statnNms.put("1284","문산");
        statnNms.put("1290","용산");
        statnNms.put("1291","서울");
        statnNms.put("1292","공덕");
        statnNms.put("1293","홍대입구");
        statnNms.put("1294","디지털미디어시티");
        statnNms.put("1305","청량리");
        statnNms.put("1306","회기");
        statnNms.put("1307","중랑");
        statnNms.put("1308","광운대");
        statnNms.put("1309","상봉");
        statnNms.put("1310","망우");
        statnNms.put("1311","신내");
        statnNms.put("1312","갈매");
        statnNms.put("1313","별내");
        statnNms.put("1314","퇴계원");
        statnNms.put("1315","사릉");
        statnNms.put("1316","금곡");
        statnNms.put("1317","평내호평");
        statnNms.put("1318","천마산");
        statnNms.put("1319","마석");
        statnNms.put("1320","대성리");
        statnNms.put("1321","청평");
        statnNms.put("1322","상천");
        statnNms.put("1323","가평");
        statnNms.put("1324","굴봉산");
        statnNms.put("1325","백양리");
        statnNms.put("1326","강촌");
        statnNms.put("1327","김유정");
        statnNms.put("1328","남춘천");
        statnNms.put("1329","춘천");
        statnNms.put("1401","봉명");
        statnNms.put("1402","쌍용(나사렛대)");
        statnNms.put("1403","아산");
        statnNms.put("1404","탕정");
        statnNms.put("1405","배방");
        statnNms.put("1406","풍기");
        statnNms.put("1407","온양온천");
        statnNms.put("1408","신창");
        statnNms.put("1450","선바위");
        statnNms.put("1451","경마공원");
        statnNms.put("1452","대공원");
        statnNms.put("1453","과천");
        statnNms.put("1454","정부과천청사");
        statnNms.put("1455","인덕원");
        statnNms.put("1456","평촌");
        statnNms.put("1457","범계");
        statnNms.put("1458","금정");
        statnNms.put("1501","판교");
        statnNms.put("1502","이매");
        statnNms.put("1503","삼동");
        statnNms.put("1504","경기광주");
        statnNms.put("1505","초월");
        statnNms.put("1506","곤지암");
        statnNms.put("1507","신둔도예촌");
        statnNms.put("1508","이천");
        statnNms.put("1509","부발");
        statnNms.put("1510","세종대왕릉");
        statnNms.put("1511","여주");
        statnNms.put("1701","구로");
        statnNms.put("1702","가산디지털단지");
        statnNms.put("1703","금천구청");
        statnNms.put("1704","석수");
        statnNms.put("1705","관악");
        statnNms.put("1706","안양");
        statnNms.put("1707","명학");
        statnNms.put("1708","금정");
        statnNms.put("1709","군포");
        statnNms.put("1710","의왕");
        statnNms.put("1711","성균관대");
        statnNms.put("1712","화서");
        statnNms.put("1713","수원");
        statnNms.put("1714","독산");
        statnNms.put("1715","세류");
        statnNms.put("1716","병점");
        statnNms.put("1717","세마");
        statnNms.put("1718","오산대");
        statnNms.put("1719","오산");
        statnNms.put("1720","진위");
        statnNms.put("1721","송탄");
        statnNms.put("1722","서정리");
        statnNms.put("1723","지제");
        statnNms.put("1724","평택");
        statnNms.put("1725","성환");
        statnNms.put("1726","직산");
        statnNms.put("1727","두정");
        statnNms.put("1728","천안");
        statnNms.put("1729","당정");
        statnNms.put("1749","서동탄");
        statnNms.put("1750","광명");
        statnNms.put("1751","산본");
        statnNms.put("1752","대야미");
        statnNms.put("1753","반월");
        statnNms.put("1754","상록수");
        statnNms.put("1755","한대앞");
        statnNms.put("1756","중앙");
        statnNms.put("1757","고잔");
        statnNms.put("1758","초지");
        statnNms.put("1759","안산");
        statnNms.put("1760","신길온천");
        statnNms.put("1761","정왕");
        statnNms.put("1762","오이도");
        statnNms.put("1763","수리산");
        statnNms.put("1801","개봉");
        statnNms.put("1802","오류동");
        statnNms.put("1803","역곡");
        statnNms.put("1804","부천");
        statnNms.put("1805","송내");
        statnNms.put("1806","부평");
        statnNms.put("1807","백운");
        statnNms.put("1808","동암");
        statnNms.put("1809","주안");
        statnNms.put("1810","제물포");
        statnNms.put("1811","동인천");
        statnNms.put("1812","인천");
        statnNms.put("1813","구일");
        statnNms.put("1814","소사");
        statnNms.put("1815","부개");
        statnNms.put("1816","간석");
        statnNms.put("1817","도원");
        statnNms.put("1821","온수");
        statnNms.put("1822","중동");
        statnNms.put("1823","도화");
        statnNms.put("1845","왕십리");
        statnNms.put("1846","수원");
        statnNms.put("1847","서울숲");
        statnNms.put("1848","압구정로데오");
        statnNms.put("1849","강남구청");
        statnNms.put("1850","선정릉");
        statnNms.put("1851","가천대");
        statnNms.put("1852","태평");
        statnNms.put("1853","모란");
        statnNms.put("1854","야탑");
        statnNms.put("1855","서현");
        statnNms.put("1856","수내");
        statnNms.put("1857","정자");
        statnNms.put("1858","미금");
        statnNms.put("1859","오리");
        statnNms.put("1860","이매");
        statnNms.put("1861","보정");
        statnNms.put("1862","죽전");
        statnNms.put("1863","구성");
        statnNms.put("1864","신갈");
        statnNms.put("1865","기흥");
        statnNms.put("1866","상갈");
        statnNms.put("1867","청명");
        statnNms.put("1868","영통");
        statnNms.put("1869","망포");
        statnNms.put("1870","매탄권선");
        statnNms.put("1871","수원시청");
        statnNms.put("1872","매교");
        statnNms.put("1877","오이도");
        statnNms.put("1878","달월");
        statnNms.put("1879","월곶");
        statnNms.put("1880","소래포구");
        statnNms.put("1881","인천논현");
        statnNms.put("1882","호구포");
        statnNms.put("1883","남동인더스파크");
        statnNms.put("1884","원인재");
        statnNms.put("1885","연수");
        statnNms.put("1886","송도");
        statnNms.put("1888","인하대");
        statnNms.put("1889","숭의");
        statnNms.put("1890","신포");
        statnNms.put("1891","인천");
        statnNms.put("1901","방학");
        statnNms.put("1902","도봉");
        statnNms.put("1903","도봉산");
        statnNms.put("1904","망월사");
        statnNms.put("1905","회룡");
        statnNms.put("1906","의정부");
        statnNms.put("1907","가능");
        statnNms.put("1908","녹양");
        statnNms.put("1909","양주");
        statnNms.put("1910","덕계");
        statnNms.put("1911","덕정");
        statnNms.put("1912","지행");
        statnNms.put("1913","동두천중앙");
        statnNms.put("1914","보산");
        statnNms.put("1915","동두천");
        statnNms.put("1916","소요산");
        statnNms.put("1948","원흥");
        statnNms.put("1950","삼송");
        statnNms.put("1951","원당");
        statnNms.put("1952","화정");
        statnNms.put("1953","대곡");
        statnNms.put("1954","백석");
        statnNms.put("1955","마두");
        statnNms.put("1956","정발산");
        statnNms.put("1957","주엽");
        statnNms.put("1958","대화");
        statnNms.put("2511","방화");
        statnNms.put("2512","개화산");
        statnNms.put("2513","김포공항");
        statnNms.put("2514","송정");
        statnNms.put("2515","마곡");
        statnNms.put("2516","발산");
        statnNms.put("2517","우장산");
        statnNms.put("2518","화곡");
        statnNms.put("2519","까치산");
        statnNms.put("2520","신정(은행정)");
        statnNms.put("2521","목동");
        statnNms.put("2522","오목교(목동운동장앞)");
        statnNms.put("2523","양평");
        statnNms.put("2524","영등포구청");
        statnNms.put("2525","영등포시장");
        statnNms.put("2526","신길");
        statnNms.put("2527","여의도");
        statnNms.put("2528","여의나루");
        statnNms.put("2529","마포");
        statnNms.put("2530","공덕");
        statnNms.put("2531","애오개");
        statnNms.put("2532","충정로");
        statnNms.put("2533","서대문");
        statnNms.put("2534","광화문");
        statnNms.put("2535","종로3가");
        statnNms.put("2536","을지로4가");
        statnNms.put("2537","동대문역사문화공원");
        statnNms.put("2538","청구");
        statnNms.put("2539","신금호");
        statnNms.put("2540","행당");
        statnNms.put("2541","왕십리");
        statnNms.put("2542","마장");
        statnNms.put("2543","답십리");
        statnNms.put("2544","장한평");
        statnNms.put("2545","군자(능동)");
        statnNms.put("2546","아차산(어린이대공원후문)");
        statnNms.put("2547","광나루(장신대)");
        statnNms.put("2548","천호(풍납토성)");
        statnNms.put("2549","강동");
        statnNms.put("2550","길동");
        statnNms.put("2551","굽은다리(강동구민회관앞)");
        statnNms.put("2552","명일");
        statnNms.put("2553","고덕");
        statnNms.put("2554","상일동");
        statnNms.put("2555","둔촌동");
        statnNms.put("2556","올림픽공원(한국체대)");
        statnNms.put("2557","방이");
        statnNms.put("2558","오금");
        statnNms.put("2559","개롱");
        statnNms.put("2560","거여");
        statnNms.put("2561","마천");
        statnNms.put("2611","응암순환(상선)");
        statnNms.put("2612","역촌");
        statnNms.put("2613","불광");
        statnNms.put("2614","독바위");
        statnNms.put("2615","연신내");
        statnNms.put("2616","구산");
        statnNms.put("2617","새절");
        statnNms.put("2618","증산(명지대앞)");
        statnNms.put("2619","디지털미디어시티");
        statnNms.put("2620","월드컵경기장(성산)");
        statnNms.put("2621","마포구청");
        statnNms.put("2622","망원");
        statnNms.put("2623","합정");
        statnNms.put("2624","상수");
        statnNms.put("2625","광흥창");
        statnNms.put("2626","대흥(서강대앞)");
        statnNms.put("2627","공덕");
        statnNms.put("2628","효창공원앞");
        statnNms.put("2629","삼각지");
        statnNms.put("2630","녹사평");
        statnNms.put("2631","이태원");
        statnNms.put("2632","한강진");
        statnNms.put("2633","버티고개");
        statnNms.put("2634","약수");
        statnNms.put("2635","청구");
        statnNms.put("2636","신당");
        statnNms.put("2637","동묘앞");
        statnNms.put("2638","창신");
        statnNms.put("2639","보문");
        statnNms.put("2640","안암(고대병원앞)");
        statnNms.put("2641","고려대");
        statnNms.put("2642","월곡(동덕여대)");
        statnNms.put("2643","상월곡(한국과학기술연구원)");
        statnNms.put("2644","돌곶이");
        statnNms.put("2645","석계");
        statnNms.put("2646","태릉입구");
        statnNms.put("2647","화랑대(서울여대입구)");
        statnNms.put("2648","봉화산");
        statnNms.put("2711","장암");
        statnNms.put("2712","도봉산");
        statnNms.put("2713","수락산");
        statnNms.put("2714","마들");
        statnNms.put("2715","노원");
        statnNms.put("2716","중계");
        statnNms.put("2717","하계");
        statnNms.put("2718","공릉(서울산업대입구)");
        statnNms.put("2719","태릉입구");
        statnNms.put("2720","먹골");
        statnNms.put("2721","중화");
        statnNms.put("2722","상봉");
        statnNms.put("2723","면목");
        statnNms.put("2724","사가정");
        statnNms.put("2725","용마산");
        statnNms.put("2726","중곡");
        statnNms.put("2727","군자(능동)");
        statnNms.put("2728","어린이대공원(세종대)");
        statnNms.put("2729","건대입구");
        statnNms.put("2730","뚝섬유원지");
        statnNms.put("2731","청담");
        statnNms.put("2732","강남구청");
        statnNms.put("2733","학동");
        statnNms.put("2734","논현");
        statnNms.put("2735","반포");
        statnNms.put("2736","고속터미널");
        statnNms.put("2737","내방");
        statnNms.put("2738","총신대입구(이수)");
        statnNms.put("2739","남성");
        statnNms.put("2740","숭실대입구(살피재)");
        statnNms.put("2741","상도(중앙대앞)");
        statnNms.put("2742","장승배기");
        statnNms.put("2743","신대방삼거리");
        statnNms.put("2744","보라매");
        statnNms.put("2745","신풍");
        statnNms.put("2746","대림");
        statnNms.put("2747","남구로");
        statnNms.put("2748","가산디지털단지");
        statnNms.put("2749","철산");
        statnNms.put("2750","광명사거리");
        statnNms.put("2751","천왕");
        statnNms.put("2752","온수");
        statnNms.put("2753","까치울");
        statnNms.put("2754","부천종합운동장");
        statnNms.put("2755","춘의");
        statnNms.put("2756","신중동");
        statnNms.put("2757","부천시청");
        statnNms.put("2758","상동");
        statnNms.put("2759","삼산체육관");
        statnNms.put("2760","굴포천");
        statnNms.put("2761","부평구청");
        statnNms.put("2811","암사");
        statnNms.put("2812","천호");
        statnNms.put("2813","강동구청");
        statnNms.put("2814","몽촌토성(평화의문)");
        statnNms.put("2815","잠실");
        statnNms.put("2816","석촌");
        statnNms.put("2817","송파");
        statnNms.put("2818","가락시장");
        statnNms.put("2819","문정");
        statnNms.put("2820","장지");
        statnNms.put("2821","복정");
        statnNms.put("2822","산성");
        statnNms.put("2823","남한산성입구");
        statnNms.put("2824","단대오거리");
        statnNms.put("2825","신흥");
        statnNms.put("2826","수진");
        statnNms.put("2827","모란");
        statnNms.put("3110","계양");
        statnNms.put("3111","귤현");
        statnNms.put("3112","박촌");
        statnNms.put("3113","임학");
        statnNms.put("3114","계산");
        statnNms.put("3115","경인교대입구");
        statnNms.put("3116","작전");
        statnNms.put("3117","갈산");
        statnNms.put("3118","부평구청");
        statnNms.put("3119","부평시장");
        statnNms.put("3120","부평");
        statnNms.put("3121","동수");
        statnNms.put("3122","부평삼거리");
        statnNms.put("3123","간석오거리");
        statnNms.put("3124","인천시청");
        statnNms.put("3125","예술회관");
        statnNms.put("3126","인천터미널");
        statnNms.put("3127","문학경기장");
        statnNms.put("3128","선학");
        statnNms.put("3129","신연수");
        statnNms.put("3130","원인재");
        statnNms.put("3131","동춘");
        statnNms.put("3132","동막");
        statnNms.put("3133","캠퍼스타운");
        statnNms.put("3134","테크노파크");
        statnNms.put("3135","지식정보단지");
        statnNms.put("3136","인천대입구");
        statnNms.put("3137","센트럴파크");
        statnNms.put("3138","국제업무지구");
        statnNms.put("3201","검단오류");
        statnNms.put("3202","왕길");
        statnNms.put("3203","검단사거리");
        statnNms.put("3204","마전");
        statnNms.put("3205","완정");
        statnNms.put("3206","독정");
        statnNms.put("3207","검암");
        statnNms.put("3208","검바위");
        statnNms.put("3209","아시아드경기장");
        statnNms.put("3210","서구청");
        statnNms.put("3211","가정");
        statnNms.put("3212","가정중앙시장");
        statnNms.put("3213","석남");
        statnNms.put("3214","서부여성회관");
        statnNms.put("3215","인천가좌");
        statnNms.put("3216","가재울");
        statnNms.put("3217","주안국가산단");
        statnNms.put("3218","주안");
        statnNms.put("3219","시민공원");
        statnNms.put("3220","석바위시장");
        statnNms.put("3221","인천시청");
        statnNms.put("3222","석천사거리");
        statnNms.put("3223","모래내시장");
        statnNms.put("3224","만수");
        statnNms.put("3225","남동구청");
        statnNms.put("3226","인천대공원");
        statnNms.put("3227","운연");
        statnNms.put("4101","개화");
        statnNms.put("4102","김포공항");
        statnNms.put("4103","공항시장");
        statnNms.put("4104","신방화");
        statnNms.put("4105","마곡나루");
        statnNms.put("4106","양천향교");
        statnNms.put("4107","가양");
        statnNms.put("4108","증미");
        statnNms.put("4109","등촌");
        statnNms.put("4110","염창");
        statnNms.put("4111","신목동");
        statnNms.put("4112","선유도");
        statnNms.put("4113","당산");
        statnNms.put("4114","국회의사당");
        statnNms.put("4115","여의도");
        statnNms.put("4116","샛강");
        statnNms.put("4117","노량진");
        statnNms.put("4118","노들");
        statnNms.put("4119","흑석");
        statnNms.put("4120","동작");
        statnNms.put("4121","구반포");
        statnNms.put("4122","신반포");
        statnNms.put("4123","고속터미널");
        statnNms.put("4124","사평");
        statnNms.put("4125","신논현");
        statnNms.put("4126","언주");
        statnNms.put("4127","선정릉");
        statnNms.put("4128","삼성중앙");
        statnNms.put("4129","봉은사");
        statnNms.put("4130","종합운동장");
        statnNms.put("4207","김포공항");
        statnNms.put("4208","계양");
        statnNms.put("4209","검암");
        statnNms.put("4210","청라국제도시");
        statnNms.put("4211","운서");
        statnNms.put("4212","공항화물청사");
        statnNms.put("4213","인천국제공항");
        statnNms.put("4217","영종");
        statnNms.put("4307","강남");
        statnNms.put("4308","양재");
        statnNms.put("4309","양재시민의숲");
        statnNms.put("4310","청계산입구");
        statnNms.put("4311","판교");
        statnNms.put("4312","정자");
        statnNms.put("4314","동천");
        statnNms.put("4315","수지구청");
        statnNms.put("4316","성복");
        statnNms.put("4317","상현");
        statnNms.put("4318","광교중앙");
        statnNms.put("4319","광교");
        statnNms.put("4501","기흥");
        statnNms.put("4502","강남대");
        statnNms.put("4503","지석");
        statnNms.put("4504","어정");
        statnNms.put("4505","동백");
        statnNms.put("4506","초당");
        statnNms.put("4508","삼가");
        statnNms.put("4509","시청·용인대");
        statnNms.put("4510","명지대");
        statnNms.put("4511","김량장");
        statnNms.put("4512","운동장·송담대");
        statnNms.put("4513","고진");
        statnNms.put("4514","보평");
        statnNms.put("4515","둔전");
        statnNms.put("4517","전대·에버랜드");
        statnNms.put("4601","발곡");
        statnNms.put("4602","회룡");
        statnNms.put("4603","범골");
        statnNms.put("4604","경전철의정부");
        statnNms.put("4605","의정부시청");
        statnNms.put("4606","흥선");
        statnNms.put("4607","의정부중앙");
        statnNms.put("4608","동오");
        statnNms.put("4609","새말");
        statnNms.put("4610","경기도청북부청사");
        statnNms.put("4611","효자");
        statnNms.put("4612","곤제");
        statnNms.put("4613","어룡");
        statnNms.put("4614","송산");
        statnNms.put("4615","탑석");
        statnNms.put("4701","북한산우이");
        statnNms.put("4702","솔밭공원");
        statnNms.put("4703","4.19민주묘지");
        statnNms.put("4704","가오리");
        statnNms.put("4705","화계");
        statnNms.put("4706","삼양");
        statnNms.put("4707","삼양사거리");
        statnNms.put("4708","솔샘");
        statnNms.put("4709","북한산보국문");
        statnNms.put("4710","정릉");
        statnNms.put("4711","성신여대입구");
        statnNms.put("4712","보문");
        statnNms.put("4713","신설동");
    }

    private static final void initialize_subwayIds()
    {
        subwayIds.put("1호선","1001");
        subwayIds.put("2호선","1002");
        subwayIds.put("3호선","1003");
        subwayIds.put("4호선","1004");
        subwayIds.put("5호선","1005");
        subwayIds.put("6호선","1006");
        subwayIds.put("7호선","1007");
        subwayIds.put("8호선","1008");
        subwayIds.put("9호선","1009");
        subwayIds.put("경의중앙선","1063");
        subwayIds.put("공항철도","1065");
        subwayIds.put("경춘선","1067");
        subwayIds.put("인천1호선","1069");
        subwayIds.put("수인선","1071");
        subwayIds.put("분당선","1075");
        subwayIds.put("신분당선","1077");
        subwayIds.put("인천2호선","1078");
        subwayIds.put("의정부","1079");
        subwayIds.put("에버라인","1080");
        subwayIds.put("경강선","1081");
        subwayIds.put("우이신설선","1092");
        subwayIds.put("서해선","1093");
    }

    private static final void initialize_lines()
    {
        lines.put("1호선",new line());
        lines.put("2호선",new line());
        lines.put("3호선",new line());
        lines.put("4호선",new line());
        lines.put("5호선",new line());
        lines.put("6호선",new line());
        lines.put("7호선",new line());
        lines.put("8호선",new line());
        lines.put("9호선",new line());
        lines.put("경의중앙선",new line());
        lines.put("공항철도",new line());
        lines.put("경춘선",new line());
        lines.put("인천1호선",new line());
        lines.put("수인선",new line());
        lines.put("분당선",new line());
        lines.put("신분당선",new line());
        lines.put("인천2호선",new line());
        lines.put("의정부",new line());
        lines.put("에버라인",new line());
        lines.put("경강선",new line());
        lines.put("우이신설선",new line());
        lines.put("서해선",new line());

        line line;

        line=lines.get("1호선");
        line.add(new subline(new String[]{"소요산","동두천","보산","동두천중앙","지행","덕정","덕계","양주","녹양","가능","의정부","회룡","망월사","도봉산","도봉","방학","창동","녹천","월계","광운대","석계","신이문","외대앞","회기","청량리","제기동","신설동","동묘앞","동대문","종로5가","종로3가","종각","시청","서울","남영","용산","노량진","대방","신길","영등포","신도림","구로"},"하행"));
        line.add(new subline(new String[]{"구로","가산디지털단지","독산","금천구청"},"하행"));
        line.add(new subline(new String[]{"금천구청","석수","관악","안양","명학","금정","군포","당정","의왕","성균관대","화서","수원","세류","병점"},"하행"));
        line.add(new subline(new String[]{"병점","세마","오산대","오산","진위","송탄","서정리","지제","평택","성환","직산","두정","천안","봉명","쌍용(나사렛대)","아산","배방","온양온천","신창"},"하행"));
        line.add(new subline(new String[]{"구로","구일","개봉","오류동","온수","역곡","소사","부천","중동","송내","부개","부평","백운","동암","간석","주안","도화","제물포","도원","동인천","인천"},"하행"));
        line.add(new subline(new String[]{"금천구청","광명"},"하행"));
        line.add(new subline(new String[]{"병점","서동탄"},"하행"));
        line.add(line.get(0).get_reverse_subline());
        line.add(line.get(1).get_reverse_subline());
        line.add(line.get(2).get_reverse_subline());
        line.add(line.get(3).get_reverse_subline());
        line.add(line.get(4).get_reverse_subline());
        line.add(line.get(5).get_reverse_subline());
        line.add(line.get(6).get_reverse_subline());
        line.get(0).link_sublines(new subline[]{line.get(1),line.get(4)});
        line.get(1).link_sublines(new subline[]{line.get(2),line.get(5)});
        line.get(2).link_sublines(new subline[]{line.get(3),line.get(6)});
        line.get(8).link_sublines(new subline[]{line.get(7)});
        line.get(9).link_sublines(new subline[]{line.get(8)});
        line.get(10).link_sublines(new subline[]{line.get(9)});
        line.get(11).link_sublines(new subline[]{line.get(7)});
        line.get(12).link_sublines(new subline[]{line.get(8)});
        line.get(13).link_sublines(new subline[]{line.get(9)});

        line=lines.get("2호선");
        line.add(new subline(new String[]{"성수","건대입구","구의","강변","잠실나루","잠실","잠실새내","종합운동장","삼성","선릉","역삼","강남","교대","서초","방배","사당","낙성대","서울대입구","봉천","신림","신대방","구로디지털단지","대림","신도림"},"내선"));
        line.add(new subline(new String[]{"신도림","문래","영등포구청","당산","합정","홍대입구","신촌","이대","아현","충정로","시청","을지로입구","을지로3가","을지로4가","동대문역사문화공원","신당","상왕십리","왕십리","한양대","뚝섬","성수"},"내선"));
        line.add(new subline(new String[]{"신설동","용두","신답","용답","성수"},"내선"));
        line.add(new subline(new String[]{"신도림","도림천","양천구청","신정네거리","까치산"},"내선"));
        line.add(line.get(0).get_reverse_subline());
        line.add(line.get(1).get_reverse_subline());
        line.add(line.get(2).get_reverse_subline());
        line.add(line.get(3).get_reverse_subline());
        line.get(0).link_sublines(new subline[]{line.get(1)});
        line.get(1).link_sublines(new subline[]{line.get(0)});
        line.get(4).link_sublines(new subline[]{line.get(5)});
        line.get(5).link_sublines(new subline[]{line.get(4)});

        line=lines.get("3호선");
        line.add(new subline(new String[]{"대화","주엽","정발산","마두","백석","대곡","화정","원당","원흥","삼송","지축","구파발","연신내","불광","녹번","홍제","무악재","독립문","경복궁","안국","종로3가","을지로3가","충무로","동대입구","약수","금호","옥수","압구정","신사","잠원","고속터미널","교대","남부터미널","양재","매봉","도곡","대치","학여울","대청","일원","수서","가락시장","경찰병원","오금"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("4호선");
        line.add(new subline(new String[]{"당고개","상계","노원","창동","쌍문","수유","미아","미아사거리","길음","성신여대입구","한성대입구","혜화","동대문","동대문역사문화공원","충무로","명동","회현","서울","숙대입구","삼각지","신용산","이촌","동작","총신대입구(이수)","사당","남태령","선바위","경마공원","대공원","과천","정부과천청사","인덕원","평촌","범계","금정","산본","수리산","대야미","반월","상록수","한대앞","중앙","고잔","초지","안산","신길온천","정왕","오이도"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("5호선");
        line.add(new subline(new String[]{"방화","개화산","김포공항","송정","마곡","발산","우장산","화곡","까치산","신정(은행정)","목동","오목교(목동운동장앞)","양평","영등포구청","영등포시장","신길","여의도","여의나루","마포","공덕","애오개","충정로","서대문","광화문","종로3가","을지로4가","동대문역사문화공원","청구","신금호","행당","왕십리","마장","답십리","장한평","군자(능동)","아차산(어린이대공원후문)","광나루(장신대)","천호(풍납토성)","강동"},"하행"));
        line.add(new subline(new String[]{"강동","길동","굽은다리(강동구민회관앞)","명일","고덕","상일동"},"하행"));
        line.add(new subline(new String[]{"강동","둔촌동","올림픽공원(한국체대)","방이","오금","개롱","거여","마천"},"하행"));
        line.add(line.get(0).get_reverse_subline());
        line.add(line.get(1).get_reverse_subline());
        line.add(line.get(2).get_reverse_subline());
        line.get(0).link_sublines(new subline[]{line.get(1),line.get(2)});
        line.get(4).link_sublines(new subline[]{line.get(3)});
        line.get(5).link_sublines(new subline[]{line.get(3)});

        line=lines.get("6호선");
        line.add(new subline(new String[]{"응암순환(상선)","역촌","불광","독바위","연신내","구산","응암순환(상선)"},"하행"));
        line.add(new subline(new String[]{"응암순환(상선)","새절(신사)","증산(명지대앞)","디지털미디어시티","월드컵경기장(성산)","마포구청","망원","합정","상수","광흥창","대흥(서강대앞)","공덕","효창공원앞","삼각지","녹사평","이태원","한강진","버티고개","약수","청구","신당","동묘앞","창신","보문","안암(고대병원앞)","고려대","월곡(동덕여대)","상월곡(한국과학기술연구원)","돌곶이","석계","태릉입구","화랑대(서울여대입구)","봉화산","신내"},"하행"));
        line.add(line.get(1).get_reverse_subline());
        line.get(0).link_sublines(new subline[]{line.get(1)});
        line.get(2).link_sublines(new subline[]{line.get(0)});

        line=lines.get("7호선");
        line.add(new subline(new String[]{"장암","도봉산","수락산","마들","노원","중계","하계","공릉(서울산업대입구)","태릉입구","먹골","중화","상봉","면목","사가정","용마산","중곡","군자(능동)","어린이대공원(세종대)","건대입구","뚝섬유원지","청담","강남구청","학동","논현","반포","고속터미널","내방","총신대입구(이수)","남성","숭실대입구(살피재)","상도(중앙대앞)","장승배기","신대방삼거리","보라매","신풍","대림","남구로","가산디지털단지","철산","광명사거리","천왕","온수","까치울","부천종합운동장","춘의역","신중동","부천시청","상동","삼산체육관","굴포천","부평구청"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("8호선");
        line.add(new subline(new String[]{"암사","천호(풍납토성)","강동구청","몽촌토성(평화의문)","잠실","석촌","송파","가락시장","문정","장지","복정","산성","남한산성입구(성남법원, 검찰청)","단대오거리","신흥","수진","모란"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("9호선");
        line.add(new subline(new String[]{"개화","김포공항","공항시장","신방화","마곡나루","양천향교","가양","증미","등촌","염창","신목동","선유도","당산","국회의사당","여의도","샛강","노량진","노들","흑석","동작","구반포","신반포","고속터미널","사평","신논현","언주","선정릉","삼성중앙","봉은사","종합운동장","삼전","석촌고분","석촌","송파나루","한성백제","올림픽공원","둔촌오륜","중앙보훈병원"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("경의중앙선");
        line.add(new subline(new String[]{"문산","파주","월롱","금촌","금릉","운정","야당","탄현","일산","풍산","백마","곡산","대곡","능곡","행신","강매","화전","수색","디지털미디어시티","가좌"},"하행"));
        line.add(new subline(new String[]{"가좌","신촌(경의.중앙선)","서울"},"하행"));
        line.add(new subline(new String[]{"가좌","홍대입구","서강대","공덕","효창공원앞","용산","이촌","서빙고","한남","옥수","응봉","왕십리","청량리","회기","중랑","상봉","망우","양원","구리","도농","양정","덕소","도심","팔당","운길산","양수","신원","국수","아신","오빈","양평","원덕","용문","지평"},"하행"));
        line.add(line.get(0).get_reverse_subline());
        line.add(line.get(1).get_reverse_subline());
        line.add(line.get(2).get_reverse_subline());
        line.get(0).link_sublines(new subline[]{line.get(1),line.get(2)});
        line.get(4).link_sublines(new subline[]{line.get(3)});
        line.get(5).link_sublines(new subline[]{line.get(3)});

        line=lines.get("공항철도");
        line.add(new subline(new String[]{"인천공항2터미널","인천공항1터미널","공항화물청사","운서","영종","청라국제도시","검암","계양","김포공항","마곡나루","디지털미디어시티","홍대입구","공덕","서울"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("경춘선");
        line.add(new subline(new String[]{"청량리","회기","중랑","상봉"},"하행"));
        line.add(new subline(new String[]{"상봉","망우","신내","갈매","별내","퇴계원","사릉","금곡","평내호평","천마산","마석","대성리","청평","상천","가평","굴봉산","백양리","강촌","김유정","남춘천","춘천"},"하행"));
        line.add(new subline(new String[]{"광운대","상봉"},"하행"));
        line.add(line.get(0).get_reverse_subline());
        line.add(line.get(1).get_reverse_subline());
        line.add(line.get(2).get_reverse_subline());
        line.get(0).link_sublines(new subline[]{line.get(1)});
        line.get(2).link_sublines(new subline[]{line.get(1)});
        line.get(4).link_sublines(new subline[]{line.get(3),line.get(5)});

        line=lines.get("인천1호선");
        line.add(new subline(new String[]{"계양","귤현","박촌","임학","계산","경인교대입구","작전","갈산","부평구청","부평시장","부평","동수","부평삼거리","간석오거리","인천시청","예술회관","인천터미널","문학경기장","선학","신연수","원인재","동춘","동막","캠퍼스타운","테크노파크","지식정보단지","인천대입구","센트럴파크","국제업무지구"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("수인선");
        line.add(new subline(new String[]{"오이도","달월","월곶","소래포구","인천논현","호구포","남동인더스파크","원인재","연수","송도","인하대","숭의","신포","인천"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("분당선");
        line.add(new subline(new String[]{"청량리","왕십리","서울숲","압구정로데오","강남구청","선정릉","선릉","한티","도곡","구룡","개포동","대모산","수서","복정","가천대","태평","모란","야탑","이매","서현","수내","정자","미금","오리","죽전","보정","구성","신갈","기흥","상갈","청명","영통","망포","매탄권선","수원시청","매교","수원"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("신분당선");
        line.add(new subline(new String[]{"강남","양재","양재시민의숲","청계산입구","판교","정자","미금","동천","수지구청","성복","상현","광교중앙","광교"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("인천2호선");
        line.add(new subline(new String[]{"검단오류","왕길","검단사거리","마전","완정","독정","검암","검바위","아시아드경기장","서구청","가정","가정중앙시장","석남","서부여성회관","인천가좌","가재울","주안국가산단","주안","시민공원","석바위시장","인천시청","석천사거리","모래내시장","만수","남동구청","인천대공원","운연"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("의정부");
        line.add(new subline(new String[]{"발곡","회룡","범골","경전철의정부","의정부시청","흥선","의정부중앙","동오","새말","경기도청북부청사","효자","곤제","어룡","송산","탑석"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("에버라인");
        line.add(new subline(new String[]{"기흥","강남대","지석","어정","동백","초당","삼가","시청·용인대","명지대","김량장","운동장·송담대","고진","보평","둔전","전대·에버랜드"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("경강선");
        line.add(new subline(new String[]{"판교","이매","삼동","경기광주","초월","곤지암","신둔도예촌","이천","부발","세종대왕릉","여주"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("우이신설선");
        line.add(new subline(new String[]{"북한산우이","솔밭공원","4.19 민주묘지","가오리","화계","삼양","삼양사거리","솔샘","북한산보국문","정릉","성신여대입구","보문","신설동"},"하행"));
        line.add(line.get(0).get_reverse_subline());

        line=lines.get("서해선");
        line.add(new subline(new String[]{"소사","소새울","시흥대야","신천","신현","시흥시청","시흥능곡","달미","선부","초지","원곡","원시"},"하행"));
        line.add(line.get(0).get_reverse_subline());
    }

    public static final class line
    {
        private final ArrayList<subline> sublines=new ArrayList<subline>();

        public final void add(subline subline)
        {
            sublines.add(subline);
        }

        public final subline get(final int index)
        {
            return sublines.get(index);
        }

        public final subline get_departure_station_subline(final String departure_station_statnNm, final int from_departure_station_to_arrival_station, final String arrival_station_statnNm)
        {
            subline departure_station_subline=null;

            for (subline subline:sublines)
            {
                if(subline.get_is_departure_station_subline(departure_station_statnNm,from_departure_station_to_arrival_station,arrival_station_statnNm)==true)
                {
                    departure_station_subline=subline;
                }
            }
            return departure_station_subline;
        }
    }

    public static final class subline
    {
        private String[] stations;
        private String updnLine;
        private subline[] next_sublines=new subline[]{};

        public subline(final String[] stations, final String updnLine)
        {
            this.stations=stations;
            this.updnLine=updnLine;
        }

        public final void link_sublines(final subline[] next_sublines)
        {
            this.next_sublines=next_sublines;
        }

        public final String get_updnLine()
        {
            return updnLine;
        }

        public final subline get_reverse_subline()
        {
            String[] reverse_stations=new String[stations.length];

            for(int index=0;index<reverse_stations.length;index++)
            {
                reverse_stations[index]=stations[stations.length-1-index];
            }

            return new subline(reverse_stations,updnLine.equals("하행")?"상행":"외선");
        }

        public final boolean get_is_departure_station_subline(final String departure_station_statnNm, final int from_departure_station_to_arrival_station, final String arrival_station_statnNm)
        {
            boolean does_contain_departure_station=false;
            int from_current_station_to_arrival_station=from_departure_station_to_arrival_station;

            for(String station:stations)
            {
                if(does_contain_departure_station==true)
                {
                    if(--from_current_station_to_arrival_station==0)
                    {
                        if (station.equals(arrival_station_statnNm))
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }

                if(station.equals(departure_station_statnNm))
                {
                    does_contain_departure_station=true;
                }
            }

            if(does_contain_departure_station==false)
            {
                return false;
            }
            else
            {
                for(subline next_subline:next_sublines)
                {
                    if(next_subline.get_is_linked_to_arrival_station(from_current_station_to_arrival_station,arrival_station_statnNm))
                    {
                        return true;
                    }
                }
                return false;
            }
        }

        public final boolean get_does_stop_at_destination_station(final String arrival_station_statnNm, final String bstatnNm)
        {
            boolean does_stopped_at_arrival_station=false;

            for(String station:stations)
            {
                if(station.equals(arrival_station_statnNm))
                {
                    does_stopped_at_arrival_station=true;
                }

                if(station.equals(bstatnNm))
                {
                    if(does_stopped_at_arrival_station==true)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            if(does_stopped_at_arrival_station==true)
            {
                for(subline next_subline:next_sublines)
                {
                    if(next_subline.get_does_stop_at_destination_station(arrival_station_statnNm,bstatnNm))
                    {
                        return true;
                    }
                }
            }
            else
            {
                for(subline next_subline:next_sublines)
                {
                    if(next_subline.get_does_stop_at_destination_station(bstatnNm))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        private final boolean get_is_linked_to_arrival_station(final int from_current_subline_to_arrival_station, final String arrival_station_statnNm)
        {
            int from_current_station_to_arrival_station=from_current_subline_to_arrival_station;

            for(int index=1;index<stations.length;index++)
            {
                from_current_station_to_arrival_station--;
                if(from_current_station_to_arrival_station==0)
                {
                    if(stations[index].equals(arrival_station_statnNm))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            for(subline next_subline:next_sublines)
            {
                if(next_subline.get_is_linked_to_arrival_station(from_current_station_to_arrival_station,arrival_station_statnNm))
                {
                    return true;
                }
            }
            return false;
        }

        private final boolean get_does_stop_at_destination_station(final String bstatnNm)
        {
            for(String station:stations)
            {
                if(station.equals(bstatnNm))
                {
                    return true;
                }
            }

            for(subline next_subline:next_sublines)
            {
                if(next_subline.get_does_stop_at_destination_station(bstatnNm))
                {
                    return true;
                }
            }
            return false;
        }
    }

    static public final String get_legacy_statnNm(final String statnNm, final String subwayNm)
    {
        if(statnNm.equals("신촌(경의.중앙선)"))
        {
            return "신촌(경의중앙선)";
        }
        else
        {
            if(statnNm.equals("양평")&&subwayNm.equals("경의중앙선"))
            {
                return "양평(경의중앙선)";
            }
            else
            {
                if(statnNm.contains("("))
                {
                    return statnNm.substring(0,statnNm.indexOf("("));
                }
                else
                {
                    return statnNm;
                }
            }
        }
    }
}