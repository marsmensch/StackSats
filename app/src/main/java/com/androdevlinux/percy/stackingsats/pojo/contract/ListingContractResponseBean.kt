package com.androdevlinux.percy.stackingsats.pojo.contract

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListingContractResponseBean {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("filters")
    @Expose
    var filters: Filters? = null

    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null

    @SerializedName("contracts")
    @Expose
    var contracts: List<Contract>? = null
}