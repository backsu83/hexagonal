package com.ebaykorea.payback.infrastructure.gateway.mapper;

import com.ebaykorea.payback.core.domain.constant.MemberType;
import com.ebaykorea.payback.core.domain.entity.order.*;
import com.ebaykorea.payback.infrastructure.gateway.client.order.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ebaykorea.payback.util.PaybackCollections.orEmptyStream;

@Mapper(componentModel = "spring")
public interface OrderGatewayMapper {

  default Order map(final OrderQueryResponseDto source) {
    return Order.of(
        source.getOrderKey(),
        source.getPaySeq(),
        mapToBuyer(source.getBuyer()),
        source.getOrderBase().getOrderDate(),
        orEmptyStream(source.getOrderUnits()).map(this::map).collect(Collectors.toUnmodifiableList()),
        orEmptyStream(source.getBundleDiscounts()).map(this::map).collect(Collectors.toUnmodifiableList()));
  }

  @Mapping(source = "source.memberType", target = "member", qualifiedByName = "mapIsMember")
  @Mapping(source = "source.smileClubMembership", target = "smileClubMember", qualifiedByName = "mapIsSmileClubMember")
  Buyer mapToBuyer(BuyerDto source);

  @Named("mapIsMember")
  default boolean mapIsMember(final MemberType memberType) {
    return memberType == MemberType.NormalMember;
  }

  @Named("mapIsSmileClubMember")
  default boolean mapIsSmileClubMember(final BuyerDto.SmileClubMembershipDto smileClubMembership) {
    return Optional.ofNullable(smileClubMembership)
        .map(BuyerDto.SmileClubMembershipDto::isSmileClubMember)
        .orElse(false);
  }

  OrderUnit map(OrderUnitDto source);

  @Mapping(source = "snapshotKey", target = "itemSnapshotKey")
  @Mapping(source = "source.branch.branchPrice", target = "branchPrice")
  OrderItem map(OrderItemDto source);

  BundleDiscount map(BundleDiscountDto source);

  List<ItemSnapshot> map(List<ItemSnapshotDto> source);

  @Mapping(source = "source.itemType", target = "isMoneyCategory", qualifiedByName = "mapIsMoneyCategory")
  @Mapping(source = "source.itemType", target = "isSmileDelivery", qualifiedByName = "mapIsSmileDelivery")
  @Mapping(source = "source.itemType", target = "isSmileFresh", qualifiedByName = "mapIsSmileFresh")
  ItemSnapshot map(ItemSnapshotDto source);

  @Named("mapIsMoneyCategory")
  default boolean mapIsMoneyCategory(ItemTypeDto source) {
    return Optional.ofNullable(source)
        .map(ItemTypeDto::getIsMoneyCategory)
        .orElse(false);
  }

  @Named("mapIsSmileDelivery")
  default boolean mapIsSmileDelivery(ItemTypeDto source) {
    return Optional.ofNullable(source)
        .map(ItemTypeDto::getIsSmileDelivery)
        .orElse(false);
  }

  @Named("mapIsSmileFresh")
  default boolean mapIsSmileFresh(ItemTypeDto source) {
    return Optional.ofNullable(source)
        .map(ItemTypeDto::getIsSmileFresh)
        .orElse(false);
  }
}
