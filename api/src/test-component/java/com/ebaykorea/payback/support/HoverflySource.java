package com.ebaykorea.payback.support;

import io.specto.hoverfly.junit.core.SimulationSource;

import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.json;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.*;

//TODO
public class HoverflySource {
  public static SimulationSource source = SimulationSource.dsl(
      service(matches("http://order-api-dev.gmarket.co.kr"))
          .get(startsWith("/v1/orders/"))
          .queryParam("fields", any())
          .willReturn(success().body(
              json("{\n"
                  + "  \"orderKey\": \"orderKey\",\n"
                  + "  \"paySeq\": \"38882683\",\n"
                  + "  \"orderBase\": {\n"
                  + "    \"buyerNo\": \"121206632\",\n"
                  + "    \"languageCode\": \"Korean\",\n"
                  + "    \"mobile\": false,\n"
                  + "    \"partnershipCode\": null,\n"
                  + "    \"orderDate\": 1669869827.712307\n"
                  + "  },\n"
                  + "  \"buyer\": {\n"
                  + "    \"buyerId\": \"bkmatrix0\",\n"
                  + "    \"buyerNo\": \"121206632\",\n"
                  + "    \"memberType\": \"NormalMember\",\n"
                  + "    \"simpleMember\": null,\n"
                  + "    \"nonMember\": null,\n"
                  + "    \"smileClubMembership\": {\n"
                  + "      \"smileClubMember\": false\n"
                  + "    }\n"
                  + "  },\n"
                  + "  \"orderUnits\": [\n"
                  + "    {\n"
                  + "      \"orderUnitKey\": \"162ddc9cc0400300vncsmgk\",\n"
                  + "      \"orderItem\": {\n"
                  + "        \"snapshotKey\": \"162ccb0d38400200hd2mhgk\",\n"
                  + "        \"itemNo\": \"1100342224\",\n"
                  + "        \"seller\": {\n"
                  + "          \"snapshotKey\": \"162ccb0d4f400200hd2mhgk\",\n"
                  + "          \"sellerNo\": \"100605608\"\n"
                  + "        },\n"
                  + "        \"basePrice\": 10000,\n"
                  + "        \"quantity\": 1,\n"
                  + "        \"options\": null,\n"
                  + "        \"additions\": null,\n"
                  + "        \"stock\": {},\n"
                  + "        \"branch\": null\n"
                  + "      },\n"
                  + "      \"itemDiscounts\": [],\n"
                  + "      \"coupons\": [],\n"
                  + "      \"smileDeliveryOption\": {\n"
                  + "        \"shippingType\": \"MidNightCutOff\",\n"
                  + "        \"expectArrivalDate\": 1669993199,\n"
                  + "        \"cutoffDate\": 1669906800\n"
                  + "      }\n"
                  + "    }\n"
                  + "  ],\n"
                  + "  \"bundleDiscounts\": []\n"
                  + "}")
          ))

          .get("/v1/snapshot/item/items")
          .queryParam("items", any())
          .willReturn(success().body(
              json("[\n"
                  + "  {\n"
                  + "    \"snapshotKey\": \"162ccb0d38400200hd2mhgk\",\n"
                  + "    \"newSnapshot\": false,\n"
                  + "    \"dtoHashCode\": 0,\n"
                  + "    \"itemNo\": \"1100342224\",\n"
                  + "    \"sellerCustNo\": \"100605608\",\n"
                  + "    \"sellerManageValue\": null,\n"
                  + "    \"itemLargeCategoryCode\": \"100000038\",\n"
                  + "    \"itemMediumCategoryCode\": \"200000773\",\n"
                  + "    \"itemSmallCategoryCode\": \"300008498\",\n"
                  + "    \"adultCategory\": false,\n"
                  + "    \"classCode\": null,\n"
                  + "    \"classKind\": null,\n"
                  + "    \"minBuyQty\": 1,\n"
                  + "    \"buyUnitQty\": 1,\n"
                  + "    \"itemName\": \"더빠른배송+동탄4L+이형N(새벽가능)\",\n"
                  + "    \"itemGlobalName\": null,\n"
                  + "    \"itemKind\": 100001,\n"
                  + "    \"itemEnglishName\": null,\n"
                  + "    \"itemOrigin\": \"기타\",\n"
                  + "    \"itemOrigin2\": \"U\",\n"
                  + "    \"discountRate\": null,\n"
                  + "    \"dealPrice\": 0,\n"
                  + "    \"dealPriceKind\": \"  \",\n"
                  + "    \"optionItemDescription\": null,\n"
                  + "    \"inventoryType\": null,\n"
                  + "    \"inventoryNo\": null,\n"
                  + "    \"optionInventoryNo\": null,\n"
                  + "    \"optionInventoryType\": null,\n"
                  + "    \"templateType\": \"OP\",\n"
                  + "    \"tradeWay\": \"T6\",\n"
                  + "    \"brandName\": null,\n"
                  + "    \"makerName\": \"기타\",\n"
                  + "    \"manufactureDate\": null,\n"
                  + "    \"attribEndDate\": 1639494000,\n"
                  + "    \"useType\": null,\n"
                  + "    \"buyerMileageRate\": 0,\n"
                  + "    \"buddyMileageRate\": 0,\n"
                  + "    \"adultItem\": false,\n"
                  + "    \"hasAddedFile\": false,\n"
                  + "    \"epin\": 0,\n"
                  + "    \"brandNo\": 0,\n"
                  + "    \"g9TradeType\": null,\n"
                  + "    \"deliveryGroupNo\": 441253826,\n"
                  + "    \"minSellOrderNo\": 5407161628,\n"
                  + "    \"shippingPolicyId\": 441253826,\n"
                  + "    \"shippingTypeCode\": 0,\n"
                  + "    \"shippingWeight\": 0.6,\n"
                  + "    \"donationItem\": null,\n"
                  + "    \"buyingLimit\": {\n"
                  + "      \"buyingLimitType\": \"Unknown\",\n"
                  + "      \"buyingLimitQuantity\": 0,\n"
                  + "      \"buyingLimitDays\": 0,\n"
                  + "      \"buyingMinimumQuantity\": 1,\n"
                  + "      \"buyingUnitQuantity\": 1\n"
                  + "    },\n"
                  + "    \"itemType\": {\n"
                  + "      \"isECoupon\": false,\n"
                  + "      \"isECouponAuth\": false,\n"
                  + "      \"isMoneyCategory\": false,\n"
                  + "      \"isRental\": false,\n"
                  + "      \"isMount\": false,\n"
                  + "      \"isZeroPrice\": false,\n"
                  + "      \"isAdult\": false,\n"
                  + "      \"isFoodDelivery\": false,\n"
                  + "      \"isSmileDelivery\": true,\n"
                  + "      \"isSmileFresh\": false,\n"
                  + "      \"isExshop\": false,\n"
                  + "      \"isIncomeDuty\": false,\n"
                  + "      \"isSmileClubDeal\": false,\n"
                  + "      \"isSmileClubBizDeal\": false,\n"
                  + "      \"isSmilePayDeal\": false,\n"
                  + "      \"isCartLimited\": false,\n"
                  + "      \"isSFCMall\": false,\n"
                  + "      \"isBusinessMan\": false,\n"
                  + "      \"isUsedItem\": false,\n"
                  + "      \"isInternationalShipping\": false,\n"
                  + "      \"isExceptGlobal\": false,\n"
                  + "      \"isOptionUse\": false,\n"
                  + "      \"isBackwoodsDelivery\": true,\n"
                  + "      \"isSmileBox\": true,\n"
                  + "      \"isTodayDelivery\": false,\n"
                  + "      \"isTaxableItem\": true,\n"
                  + "      \"isAsPossible\": false,\n"
                  + "      \"isG9DupExpose\": false,\n"
                  + "      \"isG9\": false,\n"
                  + "      \"isCashReceiptCategory\": true,\n"
                  + "      \"isDiscountAgreement\": true\n"
                  + "    },\n"
                  + "    \"sdInfo\": {\n"
                  + "      \"sdBrandId\": null,\n"
                  + "      \"sdCategoryCode\": \"00260001000200010000\"\n"
                  + "    },\n"
                  + "    \"legacy\": {\n"
                  + "      \"itemShopKindCode\": \" \",\n"
                  + "      \"itemShopKindCode2\": \"-\",\n"
                  + "      \"itemShopKindCode3\": \"-\"\n"
                  + "    },\n"
                  + "    \"shops\": [],\n"
                  + "    \"options\": [],\n"
                  + "    \"additions\": [],\n"
                  + "    \"purchaseBenefits\": [],\n"
                  + "    \"sellerManageCode\": null,\n"
                  + "    \"smileClubJoinBaseDate\": null,\n"
                  + "    \"partnershipEstimationType\": null,\n"
                  + "    \"partnershipReservationServiceType\": null\n"
                  + "  }\n"
                  + "]")
          )),

      service(matches("http://payment-api-dev.gmarket.co.kr"))
          .get(startsWith("/v1/payment-records/"))
          .willReturn(success().body(
              json("{\n"
                  + "  \"paymentSequence\": 38882683,\n"
                  + "  \"txKey\": \"txKey\",\n"
                  + "  \"buyerNo\": \"121206632\",\n"
                  + "  \"buyerId\": \"bkmatrix0\",\n"
                  + "  \"partnershipCode\": \"\",\n"
                  + "  \"mainPaymentMethod\": {\n"
                  + "    \"mediumCode\": \"200000035\",\n"
                  + "    \"smallCode\": \"300000277\",\n"
                  + "    \"amount\": 10000,\n"
                  + "    \"paymentAuthorizationSequence\": 0\n"
                  + "  },\n"
                  + "  \"subPaymentMethods\": [],\n"
                  + "  \"authentications\": {\n"
                  + "    \"alipay\": null,\n"
                  + "    \"card\": null,\n"
                  + "    \"cellPhone\": null,\n"
                  + "    \"smilePay\": {\n"
                  + "      \"certificationId\": \"1100000017330108S001\",\n"
                  + "      \"smilePayToken\": \"eyJhbGciOiJBMjU2Q0JDIiwiZW5jIjoiQTI1NkNCQyJ9.~xxIpdT9nhO56Id3ARuhLkAalq7nDEzO02jt1JqUnK5x+YnOMudAKudZPehnP8+RzU+anO32mh89kNhqvLO/ncva8S/8Ncsboa+vAe8j8ry5o7fzxS1Z+pxuViCP6pRXrOGmrmrHccpOCZ7+2nWGJJe/HxK9UuZdU0CRvaJafvKfbLjqAlrssr2IROllDmhmzQqPLKQ9eBfTPQfoGpwECi9KRzeiR6yvJGUMiVeYRyffY2+g76itdm4LR6GJgVJNrIkPM/3YFZu7XKlKDNZSJXJtElZiRmVheRqCI7u/qw4HE2RylE/u10pE54LQg7p2VpWySlTk/5fnvnbXW1+/JN40kqYkEw8i35NNMd57+WtQOQcDNlZOGh5g5ETX3dXcoYiJV0fGDbn48nhHFue1JNlaolNIR0x2dRIpJg9wlhWM\\u003d\",\n"
                  + "      \"totalMoney\": 10000,\n"
                  + "      \"cardRequestMoney\": 10000,\n"
                  + "      \"cashRequestMoney\": 0,\n"
                  + "      \"mobileRequestMoney\": 0,\n"
                  + "      \"etcRequestMoney\": 0,\n"
                  + "      \"cashReceiptEncryptionValue\": \"~k7RVYEe698Gi/UoHYTh+1Q\\u003d\\u003d\",\n"
                  + "      \"cashReceiptType\": \"PersonalSeller\",\n"
                  + "      \"cashReceiptWayType\": \"CellPhoneNumber\",\n"
                  + "      \"isFreeInstallment\": false,\n"
                  + "      \"settleGroupSequence\": null,\n"
                  + "      \"smilePayContractCode\": [\n"
                  + "        100\n"
                  + "      ],\n"
                  + "      \"smilePayItemType\": 1,\n"
                  + "      \"ePrepayRequestMoney\": 0\n"
                  + "    },\n"
                  + "    \"paypal\": null,\n"
                  + "    \"smileCash\": null,\n"
                  + "    \"wireTransfer\": null\n"
                  + "  },\n"
                  + "  \"meta\": {\n"
                  + "    \"deviceType\": \"PC\",\n"
                  + "    \"deviceAppType\": \"Web\",\n"
                  + "    \"deviceOsType\": \"Windows\",\n"
                  + "    \"deviceInfo\": \"\",\n"
                  + "    \"deviceBrowserType\": \"Chrome\",\n"
                  + "    \"clientIp\": \"192.168.24.205\",\n"
                  + "    \"serverIp\": \"\",\n"
                  + "    \"sguid\": \"11404003461724000192000000\",\n"
                  + "    \"cguid\": \"31669869615861002512200000\"\n"
                  + "  }\n"
                  + "}")
          )),

      service(matches("http://transaction-api-dev.gmarket.co.kr"))
          .get(startsWith("/v1/key/maps/txKey"))
          .willReturn(success().body(
              json("{\n"
                  + "  \"txKey\": \"txKey\",\n"
                  + "  \"orders\": [\n"
                  + "    {\n"
                  + "      \"orderUnitKey\": \"162ddc9cc0400300vncsmgk\",\n"
                  + "      \"orderKey\": \"orderKey\",\n"
                  + "      \"txKey\": \"txKey\",\n"
                  + "      \"packNo\": 4228017416,\n"
                  + "      \"orderNo\": 5408144964,\n"
                  + "      \"contrNo\": 2542078143\n"
                  + "    }\n"
                  + "  ],\n"
                  + "  \"payments\": null\n"
                  + "}")
          )),

      service(matches("http://rewardservice-dev.gmarket.co.kr"))
          .post("/api/Read/V2/CashbackReward")
          .body(equalsToJson(json("{\n"
              + "  \"TotalPrice\": 10000,\n"
              + "  \"Goods\": [\n"
              + "    {\n"
              + "      \"Key\": \"5408144964\",\n"
              + "      \"SiteCd\": 0,\n"
              + "      \"GdNo\": \"1100342224\",\n"
              + "      \"GdlcCd\": \"100000038\",\n"
              + "      \"GdmcCd\": \"200000773\",\n"
              + "      \"GdscCd\": \"300008498\",\n"
              + "      \"ScNo\": \"100605608\",\n"
              + "      \"IsSmileClub\": false,\n"
              + "      \"IsSmileDelivery\": false,\n"
              + "      \"IsSmileFresh\": false,\n"
              + "      \"Qty\": 1,\n"
              + "      \"Price\": 10000,\n"
              + "      \"marketabilityItemYn\": \"N\"\n"
              + "    }\n"
              + "  ]\n"
              + "}")))
          .willReturn(success().body(
              json("{\n"
                  + "  \"ReturnBase\": {\n"
                  + "    \"ReturnCode\": \"0000\",\n"
                  + "    \"ReturnValue\": null,\n"
                  + "    \"ErrorMessage\": null\n"
                  + "  },\n"
                  + "  \"Result\": {\n"
                  + "    \"TotalItemCashbackAmount\": 0,\n"
                  + "    \"TotalNSPCashbackAmount\": 0,\n"
                  + "    \"IfSmileCardCashbackAmount\": 200,\n"
                  + "    \"IfNewSmileCardCashbackAmount\": 200,\n"
                  + "    \"TotalAutoChargeAmount\": 100,\n"
                  + "    \"TotalAutoChargeClubAmount\": 200,\n"
                  + "    \"UseEnableDate\": \"2023-01-01\",\n"
                  + "    \"Goods\": [\n"
                  + "      {\n"
                  + "        \"ClubDayExpectSaveAmount\": 0,\n"
                  + "        \"ClubDayExpectSaveRate\": 0,\n"
                  + "        \"Key\": \"5408144964\",\n"
                  + "        \"GdNo\": \"1100342224\",\n"
                  + "        \"IfSmileClubCashbackAmount\": 0,\n"
                  + "        \"CashbackInfo\": [\n"
                  + "          {\n"
                  + "            \"CashbackCd\": 2,\n"
                  + "            \"CashbackAmount\": 0,\n"
                  + "            \"CashbackSeq\": 11224,\n"
                  + "            \"PayType\": \"P\",\n"
                  + "            \"PayRate\": 0,\n"
                  + "            \"PayMaxMoney\": 0,\n"
                  + "            \"CashbackTitle\": \"스마일페이결제\",\n"
                  + "            \"EtcTitle\": null,\n"
                  + "            \"EtcContent\": null\n"
                  + "          },\n"
                  + "          {\n"
                  + "            \"CashbackCd\": 5,\n"
                  + "            \"CashbackAmount\": 100,\n"
                  + "            \"CashbackSeq\": 11224,\n"
                  + "            \"PayType\": \"A\",\n"
                  + "            \"PayRate\": 1,\n"
                  + "            \"PayMaxMoney\": 5000,\n"
                  + "            \"CashbackTitle\": \"자동충전결제\",\n"
                  + "            \"EtcTitle\": null,\n"
                  + "            \"EtcContent\": null\n"
                  + "          }\n"
                  + "        ],\n"
                  + "        \"ItemCashbackInfo\": {\n"
                  + "          \"ItemAmount\": 0,\n"
                  + "          \"SellerAmount\": 0,\n"
                  + "          \"CategoryAmount\": 0,\n"
                  + "          \"SellerCategoryAmount\": 0,\n"
                  + "          \"ShopAmount\": 0,\n"
                  + "          \"EtcAmount\": 0\n"
                  + "        },\n"
                  + "        \"NSPCashbackInfo\": {\n"
                  + "          \"PayAmount\": 0,\n"
                  + "          \"ClubAmount\": 0,\n"
                  + "          \"AutoChargeAmount\": 100,\n"
                  + "          \"AutoChargeClubAmount\": 200\n"
                  + "        },\n"
                  + "        \"ClubDayCashbackInfo\": {\n"
                  + "          \"ClubDay\": null,\n"
                  + "          \"ItemType\": null,\n"
                  + "          \"ClubMemberType\": null,\n"
                  + "          \"ClubDayRate\": 0,\n"
                  + "          \"ClubDayMaxAmount\": 0,\n"
                  + "          \"ClubDayAmount\": 0\n"
                  + "        },\n"
                  + "        \"IfSmileCardT2T3CashbackAmount\": 0\n"
                  + "      }\n"
                  + "    ]\n"
                  + "  }\n"
                  + "}")
          ))

          .post("/api/Read/V2/CashbackRewardBackend")
          .body(equalsToJson(json("{\n"
              + "  \"TotalPrice\": 10000,\n"
              + "  \"Goods\": [\n"
              + "    {\n"
              + "      \"Key\": \"5408144964\",\n"
              + "      \"SiteCd\": 0,\n"
              + "      \"GdNo\": \"1100342224\",\n"
              + "      \"GdlcCd\": \"100000038\",\n"
              + "      \"GdmcCd\": \"200000773\",\n"
              + "      \"GdscCd\": \"300008498\",\n"
              + "      \"ScNo\": \"100605608\",\n"
              + "      \"IsSmileClub\": false,\n"
              + "      \"IsSmileDelivery\": false,\n"
              + "      \"IsSmileFresh\": false,\n"
              + "      \"Qty\": 1,\n"
              + "      \"Price\": 10000,\n"
              + "      \"marketabilityItemYn\": \"N\"\n"
              + "    }\n"
              + "  ]\n"
              + "}")))
          .willReturn(success().body(
              json("{\n"
                  + "  \"ReturnBase\": {\n"
                  + "    \"ReturnCode\": \"0000\",\n"
                  + "    \"ReturnValue\": null,\n"
                  + "    \"ErrorMessage\": null\n"
                  + "  },\n"
                  + "  \"Result\": [\n"
                  + "    {\n"
                  + "      \"Key\": \"5408144964\",\n"
                  + "      \"GdNo\": \"1100342224\",\n"
                  + "      \"CashbackSeq\": 11224,\n"
                  + "      \"CashbackCode\": 2,\n"
                  + "      \"CashbackTitle\": \"\",\n"
                  + "      \"UseEnableDate\": \"2023-01-01\",\n"
                  + "      \"CashbackMoney\": 0,\n"
                  + "      \"PayType\": 1,\n"
                  + "      \"PayRate\": 0,\n"
                  + "      \"PayMaxMoney\": 0,\n"
                  + "      \"ChargePayRewardRate\": 1,\n"
                  + "      \"ChargePayRewardClubRate\": 2,\n"
                  + "      \"ChargePayRewardSmileDeliveryRate\": 0,\n"
                  + "      \"ChargePayRewardSmileFreshRate\": 0,\n"
                  + "      \"ChargePayRewardSmileDeliveryClubRate\": 0,\n"
                  + "      \"ChargePayRewardSmileFreshClubRate\": 0,\n"
                  + "      \"ChargePayRewardMaxMoney\": 5000,\n"
                  + "      \"ChargePayRewardClubMaxMoney\": 5000,\n"
                  + "      \"ChargeCashbackMoney\": 100,\n"
                  + "      \"ChargeClubCashbackMoney\": 200,\n"
                  + "      \"ClubDay\": \"1111100\",\n"
                  + "      \"ClubDayPayRate\": 0,\n"
                  + "      \"ClubDaySaveMaxMoney\": 0,\n"
                  + "      \"ClubDayCashbackMoney\": 0\n"
                  + "    }\n"
                  + "  ]\n"
                  + "}")
          )),

      service(matches("https://quilt-overmind-dev.gmarket.co.kr"))
          .get(startsWith("/api/smilecash/smileUserKey"))
          .willReturn(success().body(
              json("{\n"
                  + "  \"resultCode\": 0,\n"
                  + "  \"message\": \"SUCCESS\",\n"
                  + "  \"data\": \"~E1BXAte+hBIBIywxCf2kTwwZWqKpcrP1O7VpT7VwXmcl2Tb/rbaZpeH2VwXtnlyLQJT7wPqmJx1jZjn/IwHnWA\\u003d\\u003d\"\n"
                  + "}")
          )),

      service(matches("https://clubapi-dev.gmarket.co.kr"))
          .get("/member/gmkt/121206632/synopsis")
          .willReturn(success().body(
              json("{\n"
                  + "  \"ResultCode\": 0,\n"
                  + "  \"Message\": \"Success\",\n"
                  + "  \"Data\": {\n"
                  + "    \"Member\": {\n"
                  + "      \"IsSmileClubMember\": \"False\",\n"
                  + "      \"UnifyMasterId\": 23209519,\n"
                  + "      \"Status\": \"S4\",\n"
                  + "      \"StartDate\": \"2018-05-11T11:22:06\",\n"
                  + "      \"EndDate\": \"\",\n"
                  + "      \"IsToPaidTarget\": \"False\",\n"
                  + "      \"Name\": \"정명환\",\n"
                  + "      \"MemberType\": \"P\",\n"
                  + "      \"MembershipGrade\": \"BASC\",\n"
                  + "      \"PayCycleType\": \"ANNL\",\n"
                  + "      \"PartnerID\": \"S001\",\n"
                  + "      \"IsUnifyMembership\": false,\n"
                  + "      \"MembershipStartDate\": \"\"\n"
                  + "    },\n"
                  + "    \"Benefit\": {\n"
                  + "      \"IsWelcomeGiftOffered\": \"\",\n"
                  + "      \"WelcomeGiftType\": \"UNKNOWN\",\n"
                  + "      \"WelcomeGiftAmount\": 0,\n"
                  + "      \"WelcomeGiftInsertDate\": \"\",\n"
                  + "      \"WelcomeGiftOfferDate\": \"\",\n"
                  + "      \"WelcomeGiftOfferExpectDate\": \"\",\n"
                  + "      \"WelcomeGiftRecallDate\": \"\",\n"
                  + "      \"AvailableWelcomeGiftAmount\": 35000,\n"
                  + "      \"AvailableWelcomeGiftType\": \"SMILECASH\",\n"
                  + "      \"TotalAmount\": 35000\n"
                  + "    },\n"
                  + "    \"Payment\": {\n"
                  + "      \"AnnualFeePayType\": \"UNKNOWN\",\n"
                  + "      \"AnnualFeePayRequestDate\": \"\",\n"
                  + "      \"AnnualFeePayFinishDate\": \"\",\n"
                  + "      \"AnnualFeePayAmount\": 0,\n"
                  + "      \"AnnualFeePayStatus\": \"\",\n"
                  + "      \"AnnualFeePayMessage\": \"\",\n"
                  + "      \"IsUsing\": \"\",\n"
                  + "      \"LastAutoOrderDate\": \"\",\n"
                  + "      \"LastAutoOrderAmount\": 0,\n"
                  + "      \"UpcomingAutoOrderDate\": \"\",\n"
                  + "      \"UpcomingAutoOrderAmount\": 0\n"
                  + "    },\n"
                  + "    \"Subscription\": {\n"
                  + "      \"SubscriptionNo\": 0,\n"
                  + "      \"PolicyNo\": 0,\n"
                  + "      \"PolicyName\": \"\",\n"
                  + "      \"SubscriptionStartDate\": \"\",\n"
                  + "      \"SubscriptionEndDate\": \"\",\n"
                  + "      \"SubscriptionSubNo\": 0,\n"
                  + "      \"SubscriptionSubItem\": \"\",\n"
                  + "      \"SubscriptionSubCost\": 0,\n"
                  + "      \"SubscriptionSubStartDate\": \"\",\n"
                  + "      \"SubscriptionSubEndDate\": \"\",\n"
                  + "      \"MembershipGrade\": \"\",\n"
                  + "      \"PayCycleType\": \"\"\n"
                  + "    }\n"
                  + "  }\n"
                  + "}")
          ))
  );
}