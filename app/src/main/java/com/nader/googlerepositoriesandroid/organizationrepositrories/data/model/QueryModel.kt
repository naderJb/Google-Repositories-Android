package com.nader.googlerepositoriesandroid.organizationrepositrories.data.model

import com.nader.googlerepositoriesandroid.base.model.OrgEnum
import com.nader.googlerepositoriesandroid.base.utils.GlobalVars.EMPTY


data class QueryModel(
    var org: String = OrgEnum.GOOGLE.org,
    var query: String = EMPTY
) {
    fun toQuery() = "user:${this.org}+${this.query}"
}