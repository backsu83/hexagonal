package com.ebaykorea.payback.grocery

import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.ClubDataDto
import com.ebaykorea.payback.infrastructure.gateway.client.club.dto.SmileClubSubscriptionDto

class ClubApiGrocery {
    static def clubDataDto_생성(Map map = [:]) {
        new ClubDataDto(
            (map.member ?: new SmileClubSubscriptionDto(
                    (map.unifyMasterId ?: 23268003L) as Long,
                    (map.membershipGrade ?: "BASC") as String,
                    (map.payCycleType ?: "ANNL") as String,
                    (map.partnerId ?: "S001") as String
            )) as SmileClubSubscriptionDto
        )
    }
}
