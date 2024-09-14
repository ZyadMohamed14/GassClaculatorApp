package com.example.gassapp.domain.models.model;

import java.util.ArrayList;
import java.util.List;

public class CarsModel {

    public List<String>getBrands(){
        List<String> brands = new ArrayList<>();
        brands.add("Please Select Brand");
        brands.add("Audi");
        brands.add("BMW");
        brands.add("Chevrolet");
        brands.add("Datsun");
        brands.add("Ford");
        return brands;
    }
    public List<String>getAudiModels(){
        List<String> audiModels = new ArrayList<>();

        // Add models to the list
        audiModels.add("A4 2.0 TDI (177bhp) Premium Plus");
        audiModels.add("A4 2.0 TDI (143 bhp)");
        audiModels.add("A3 35 TDI Technology + Sunroof");
        audiModels.add("Q3 35 TDI quattro Premium Plus");
        audiModels.add("A6 2.0 TDI Premium");
        audiModels.add("A3 35 TDI Technology + Sunroof");
        audiModels.add("Q3 35 TDI quattro Premium Plus");
        audiModels.add("A6 2.0 TDI Premium");
        audiModels.add("A6 35 TDI Matrix");
        audiModels.add("A6 35 TDI Premium");
        audiModels.add("Q7 45 TDI Premium Plus");
        audiModels.add("Q5 45 TFSI Premium Plus");
        audiModels.add("A6 2.0 TFSi Technology Pack");
        audiModels.add("A6 2.0 TDI Premium");
        audiModels.add("Q7 35 TDI Premium + Sunroof");
        audiModels.add("Q3 35 TDI quattro Premium Plus");
        audiModels.add("Q3 2.0 TDI quattro Premium Plus");
        audiModels.add("Q3 35 TDI Premium + Sunroof");
        audiModels.add("RS5 4.2 Coupe");
        audiModels.add("A4 2.0 TDI (143 bhp)");
        audiModels.add("Q3 30 TDI Premium FWD");
        audiModels.add("A4 35 TDI Technology");
        // ... and so on, up to the last model
      return audiModels;
    }
    public List<String>getBmwModels(){
        List<String> bmwList = new ArrayList<>();

        bmwList.add("X1 xDrive20d M Sport");
        bmwList.add("X3 xDrive 20d Luxury Line [2018-2020]");
        bmwList.add("3-Series 320i Luxury Line");
        bmwList.add("X3 xDrive20d");
        bmwList.add("X1 sDrive20d xLine");
        bmwList.add("X5 xDrive30d Pure Experience (5 Seater)");
        bmwList.add("X1 sDrive20d xLine");
        bmwList.add("5-Series 520d Sedan");
        bmwList.add("3-Series 320d Luxury Line");
        bmwList.add("5-Series 520d Sedan");
        bmwList.add("5-Series 530d M Sport [2013-2017]");
        bmwList.add("X5 xDrive 30d M Sport");
        bmwList.add("X5 xDrive 30d");
        bmwList.add("X5 xDrive30d Pure Experience (5 Seater)");
        bmwList.add("X1 sDrive20d Expedition");
        bmwList.add("3-Series 320i Luxury Line");
        bmwList.add("5-Series 525d Sedan");
        bmwList.add("X3 xDrive20d");
        bmwList.add("5-Series 520d Luxury Line [2017-2019]");
        bmwList.add("3-Series 320d Luxury Edition");
        return bmwList;
    }
    public List<String>getChevroletModels(){
        List<String> carList = new ArrayList<>();

        carList.add("Cruze LTZ AT");
        carList.add("Cruze LTZ");
        carList.add("Captiva LTZ AWD AT");
        carList.add("Cruze LTZ");
        carList.add("Beat LT Diesel");
        carList.add("Spark LS 1.0 BS-III");
        carList.add("Sail Sedan 1.2 LS");
        return carList;
    }
    public List<String>getDatsunModels(){
        List<String> carList = new ArrayList<>();

        // Add the new cars
        carList.add("Go D");
        carList.add("Redigo S 1.0 AMT [2018-2019]");
        carList.add("Go T (O)");
        carList.add("Redigo T(O) 1.0 [2017-2019]");
        carList.add("Go Plus T");
        carList.add("Redigo T(O) 1.0");
        carList.add("Go T (O)");
        carList.add("Redigo T(O) 1.0");

        return carList;
    }
    public  List<String>getFordModels(){
        List<String> carList = new ArrayList<>();

        // Add the new cars
        carList.add("Ecosport Titanium+ 1.0L EcoBoost");
        carList.add("Figo Duratorq Diesel Titanium 1.4");
        carList.add("Figo Duratorq Diesel ZXI 1.4");
        carList.add("Ecosport Titanium+ 1.5L TDCi");
        carList.add("Fiesta Style Diesel [2011-2014]");
        carList.add("Ecosport Trend 1.5 TDCi");
        carList.add("Ecosport Ambiente 1.5L TDCi");
        carList.add("Figo Duratec Petrol Titanium 1.2");
        carList.add("Ecosport Titanium 1.5L TDCi");
        carList.add("Aspire Titanium1.5 TDCi");
        carList.add("Endeavour Titanium 3.2 4x4 AT");
        carList.add("Endeavour 3.0L 4x4 AT");
        carList.add("Ecosport Trend 1.5 Ti-VCT");
        carList.add("Endeavour Titanium Plus 3.2 4x4 AT");
        carList.add("Ecosport Trend 1.5 TDCi");
        carList.add("Ecosport Ambiente 1.5L TDCi");
        carList.add("Aspire Titanium 1.2 Ti-VCT");
        carList.add("Ecosport Titanium 1.5L Ti-VCT");
        carList.add("Endeavour Titanium 2.0 4x2 AT");
        carList.add("Figo Trend 1.5L TDCi [2015-2016]");
        carList.add("Mustang GT Fastback 5.0L v8");
        carList.add("Ecosport Titanium 1.5 Ti-VCT");
        carList.add("Ikon DuraTorq 1.4 TDCi");
        carList.add("Endeavour Trend 2.2 4x2 AT");
        carList.add("Ecosport Trend 1.5 Ti-VCT");
        carList.add("Endeavour Sport 2.0 4x4 AT");
        carList.add("Figo Duratorq Diesel Titanium 1.4");
        carList.add("Endeavour Sport 2.0 4x4 AT");
        carList.add("Ecosport Titanium 1.5L TDCi");
        carList.add("Ecosport Titanium + 1.5L TDCi");
        carList.add("Figo Duratorq Diesel ZXI 1.4");
        carList.add("Figo Titanium1.5 TDCi");
        carList.add("Endeavour Titanium 3.2 4x4 AT");
        carList.add("Ecosport Titanium+ 1.5L TDCi [2019-2020]");
        carList.add("Ecosport Trend 1.5 TDCi");
        carList.add("Ecosport Ambiente 1.5L Ti-VCT");
        carList.add("Endeavour Titanium 2.0 4x2 AT");
        carList.add("Mustang GT Fastback 5.0L v8");
        carList.add("Endeavour Titanium 3.2 4x4 AT");
        carList.add("Aspire Titanium1.5 TDCi [2018-2020]");
        carList.add("Endeavour Titanium Plus 3.2 4x4 AT");
        carList.add("Ecosport Titanium+ 1.5L TDCi");
        carList.add("Endeavour Titanium 3.2 4x4 AT");
        carList.add("Ecosport Titanium 1.5 TDCi");
        carList.add("Ecosport Titanium 1.5L TDCi");
        carList.add("Fiesta Titanium Diesel");

        return carList;
    }

}
