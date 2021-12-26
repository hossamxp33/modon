package com.tarwej.modon.entities.cities

data class Pagination(
    var count: Int,
    var current_page: Int,
    var has_next_page: Boolean,
    var has_prev_page: Boolean,
    var limit: Any,
    var page_count: Int
)