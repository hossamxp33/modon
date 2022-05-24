package com.tarwej.modon.entities.trips

data class Transportation(
    var created: String?=null,
    var id: Int?=null,
    var modified: String?=null,
    var name: String?=null,
    var name_en: String?=null,
    var transportation_brand: TransportationBrand?=null,
    var transportation_brands_id: Int?=null,
    var transportation_type: TransportationType?=null,
    var transportation_types_id: Int?=null
)