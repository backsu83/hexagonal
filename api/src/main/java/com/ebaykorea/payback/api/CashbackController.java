package com.ebaykorea.payback.api;

import com.ebaykorea.payback.api.dto.CashbackResponseDto;
import com.ebaykorea.payback.api.dto.SaveCashbackRequestDto;
import com.ebaykorea.payback.api.dto.common.CommonResponse;
import com.ebaykorea.payback.core.CashbackApplicationService;
import com.ebaykorea.payback.infrastructure.query.CashbackQuery;
import com.ebaykorea.payback.infrastructure.query.data.SavedCashbackQueryResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Cashback", description = "캐시백 관련 Api")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CashbackController {

  private final CashbackApplicationService applicationService;
  private final CashbackQuery cashbackQuery;

  /**
   * 캐시백 데이터 저장
   *
   * @param request
   * @return
   */
  @PostMapping("/cashbacks")
  public CommonResponse<CashbackResponseDto> saveCashbacks(final @Valid @RequestBody SaveCashbackRequestDto request) {
    final var responseMessageType = applicationService.setCashback(request.getTxKey(), request.getOrderKey());

    return CommonResponse.success(responseMessageType, CashbackResponseDto.of(request.getTxKey(), request.getOrderKey()));
  }

  @GetMapping("/cashbacks")
  public SavedCashbackQueryResult getSavedCashbacks(
      @RequestParam(value = "packNo", required = false) final Long packNo,
      @RequestParam(value = "txKey", required = false) final String txKey,
      @RequestParam(value = "orderKey", required = false) final String orderKey
  ) {
    return cashbackQuery.getSavedCashback(packNo, txKey, orderKey);
  }
}

