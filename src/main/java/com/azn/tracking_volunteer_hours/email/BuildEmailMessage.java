package com.azn.tracking_volunteer_hours.email;

import java.util.Random;

public class BuildEmailMessage {
    public static String buildEmailHonors(String name) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c;background-color:#f6f8fa;padding:20px\">\n" +
                "\n" +
                "  <div style=\"font-size:28px;font-weight:bold;color:#ffffff;text-align:center;margin-bottom:10px;background-color:#000000;height:50px;line-height:50px\">\n" +
                "    <span style=\"color:#ffffff;\">ЗАТВЕРДЖЕНО</span>\n" +
                "  </div>\n" +
                "\n" +
                "  <div style=\"font-size:19px;line-height:25px;color:#0b0c0c;margin-bottom:20px;text-align:center\">\n" +
                "    Указом Президента України<br>\n" +
                "    від 5 грудня 2022 року № 825/2022<br><br>\n" +
                "    ПОЛОЖЕННЯ<br>\n" +
                "    про відзнаку Президента України \"Золоте серце\"<br><br>\n" + "</div>"+
                "    1. Відзнакою Президента України \"Золоте серце\" нагороджуються громадян(ка) України – " + name+ ", за те, що зробив(ла) вагомий внесок у надання волонтерської допомоги та розвиток волонтерського руху, зокрема під час здійснення заходів із забезпечення оборони України, захисту безпеки населення та інтересів держави у зв'язку з військовою агресією Російської Федерації проти України та подолання її наслідків.<br><br>\n" +
                "    2. Нагородження відзнакою може бути проведено посмертно.<br><br>\n" +
                "    3. Нагородження відзнакою проводиться Указом Президента України.<br><br>\n" +
                "    4. Одна й та сама особа може бути нагороджена відзнакою не більше одного разу.<br><br>\n" +
                "    5. Представлення до нагородження відзнакою проводиться відповідно до Порядку представлення до нагородження та вручення державних нагород України, затвердженого Указом Президента України від 19 лютого 2003 року № 138/2003.<br><br>\n" +
                "    6. Вручення відзнаки провадиться в урочистій обстановці Президентом України або від його імені керівниками центральних органів виконавчої влади, державних органів, що здійснюють керівництво утвореними відповідно до законів військовими формуваннями, керівниками інших державних органів, головами обласних та Київської міської державних адміністрацій, керівниками закордонних дипломатичних установ України.<br><br>\n" +
                "    7. Відзнака виготовляється із позолоченого срібла і має вигляд стилізованого зображення серця, утвореного зігнутою пластиною з отворами. Крізь отвори пропущені синя та жовта нитки, які перехрещуються.<br><br>\n" +
                "    Розміри відзнаки: висота - 40 мм, ширина - 30 мм.<br><br>\n" +
                "    На зворотному боці відзнаки - застібка для прикріплення до одягу.<br><br>\n" +
                "    8. Особі, нагородженій відзнакою, разом із відзнакою вручається документ, що посвідчує нагородження.<br><br>\n" +
                "    9. Відзнаку носять на грудях зліва і за наявності у нагородженого інших державних нагород України розміщують після них.<br><br>\n" +
                "    Президент України Володимир Зеленський<br>\t\n" +
                "  <img src=\"https://zakon.rada.gov.ua/laws/file/imgs/102/p521444n28.jpg\" alt=\"МАЛЮНОК\n" +
                "відзнаки Президента України \"Золоте серце\"\" style=\"display:block;margin:auto;width:200px;height:200px;margin-top:20px;\">\n" +
                "       \n" +
                "\n" +
                "</div>";
    }



    public static String buildEmailTicket(String userName,String event) {
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;
        return
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Movie Ticket</title>\n" +
                        "    <style>\n" +
                        "        * {\n" +
                        "            box-sizing: border-box;\n" +
                        "        }\n" +
                        "        html, body {\n" +
                        "            height: 100%;\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "        body {\n" +
                        "            font-family: sans-serif;\n" +
                        "            background-color: #3f32e5;\n" +
                        "            color: #fff;\n" +
                        "            display: flex;\n" +
                        "            justify-content: center;\n" +
                        "            align-items: center;\n" +
                        "        }\n" +
                        "        .cardWrap {\n" +
                        "            width: 27em;\n" +
                        "            margin: 3em auto;\n" +
                        "        }\n" +
                        "        .card {\n" +
                        "            background: linear-gradient(to bottom, #e84c3d 0%, #e84c3d 26%, #ecedef 26%, #ecedef 100%);\n" +
                        "            height: 10em;\n" +
                        "            float: left;\n" +
                        "            position: relative;\n" +
                        "            padding: 1em;\n" +
                        "            margin-top: 100px;\n" +
                        "        }\n" +
                        "        .cardLeft {\n" +
                        "            border-top-left-radius: 8px;\n" +
                        "            border-bottom-left-radius: 8px;\n" +
                        "            width: 16em;\n" +
                        "        }\n" +
                        "        .cardRight {\n" +
                        "            width: 6.5em;\n" +
                        "            border-left: .18em dashed #fff;\n" +
                        "            border-top-right-radius: 8px;\n" +
                        "            border-bottom-right-radius: 8px;\n" +
                        "        }\n" +
                        "        .cardRight:before,\n" +
                        "        .cardRight:after {\n" +
                        "            content: \"\";\n" +
                        "            position: absolute;\n" +
                        "            display: block;\n" +
                        "            width: .9em;\n" +
                        "            height: .9em;\n" +
                        "            background: #fff;\n" +
                        "            border-radius: 50%;\n" +
                        "            left: -.5em;\n" +
                        "        }\n" +
                        "        .cardRight:before {\n" +
                        "            top: -.4em;\n" +
                        "        }\n" +
                        "        .cardRight:after {\n" +
                        "            bottom: -.4em;\n" +
                        "        }\n" +
                        "        h1 {\n" +
                        "            font-size: 1.1em;\n" +
                        "            margin-top: 0;\n" +
                        "        }\n" +
                        "        h1 span {\n" +
                        "            font-weight: normal;\n" +
                        "        }\n" +
                        "        .title,\n" +
                        "        .name,\n" +
                        "        .seat,\n" +
                        "        .time {\n" +
                        "            text-transform: uppercase;\n" +
                        "            font-weight: normal;\n" +
                        "        }\n" +
                        "        .title h2,\n" +
                        "        .name h2,\n" +
                        "        .seat h2,\n" +
                        "        .time h2 {\n" +
                        "            font-size: .9em;\n" +
                        "            color: #525252;\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "        .title span,\n" +
                        "        .name span {\n" +
                        "            font-size: .7em;\n" +
                        "            color: #a2aeae;\n" +
                        "        }\n" +
                        "        .title {\n" +
                        "            margin: 2em 0 0 0;\n" +
                        "        }\n" +
                        "        .name,\n" +
                        "        .seat {\n" +
                        "            margin: .7em 0 0 0;\n" +
                        "        }\n" +
                        "        .time {\n" +
                        "            margin: .7em 0 0 1em;\n" +
                        "        }\n" +
                        "        .seat,\n" +
                        "        .time {\n" +
                        "            float: left;\n" +
                        "        }\n" +
                        "\n" +
                        "        .number {\n" +
                        "            text-align: center;\n" +
                        "            text-transform: uppercase;\n" +
                        "        }\n" +
                        "        .number h3 {\n" +
                        "            color: #e84c3d;\n" +
                        "            margin: .9em 0 0 0;\n" +
                        "            font-size: 2.5em;\n" +
                        "        }\n" +
                        "        .number span {\n" +
                        "            display: block;\n" +
                        "            color: #a2aeae;\n" +
                        "        }\n" +
                        "        .barcode {\n" +
                        "            height: 2em;\n" +
                        "            width: 0;\n" +
                        "            margin: 1.2em 0 0 .8em;\n" +
                        "            box-shadow: 1px 0 0 1px #343434, 5px 0 0 1px #343434, 10px 0 0 1px #343434, 11px 0 0 1px #343434, 15px 0 0 1px #343434, 18px 0 0 1px #343434, 22px 0 0 1px #343434, 23px 0 0 1px #343434, 26px 0 0 1px #343434, 30px 0 0 1px #343434, 35px 0 0 1px #343434, 37px 0 0 1px #343434, 41px 0 0 1px #343434, 44px 0 0 1px #343434, 47px 0 0 1px #343434, 51px 0 0 1px #343434, 56px 0 0 1px #343434, 59px 0 0 1px #343434, 64px 0 0 1px #343434, 68px 0 0 1px #343434, 72px 0 0 1px #343434, 74px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div class=\"cardWrap\">\n" +
                        "    <div class=\"card cardLeft\">\n" +
                        "        <h1>Ticket </h1>\n" +
                        "        <div class=\"title\">\n" +
                        "            <h2>" + event + "</h2>\n" +
                        "            <span>EVENT</span>\n" +
                        "        </div>\n" +
                        "        <div class=\"name\">\n" +
                        "            <h2>" + userName + "</h2>\n" +
                        "            <span>name</span>\n" +
                        "        </div>\n" +
                        "\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <div class=\"card cardRight\">\n" +
                        "\n" +
                        "        <div class=\"number\">\n" +
                        "            <h3>" + randomNumber + "</h3>\n" +
                        "            <span>Place</span>\n" +
                        "        </div>\n" +
                        "\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "</html>";
    }
}
