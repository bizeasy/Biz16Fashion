    <div class="infoRow">
        <div class="infoEntry">
            <div class="infoCaption"><label>${uiLabelMap.CouponCodeCaption}</label></div>
            <div class="infoValue">
               <input type="text" id="manualOfferCode" name="manualOfferCode" value="${parameters.UofferCode!""}" maxlength="20"/>
               <a href="javascript:addManualPromoCode();"><span class="refreshIcon"></span></a>
            </div>
        </div>
    </div>
    <div class="infoRow">
    ${screens.render("component://osafeadmin/widget/AdminCheckoutScreens.xml#enteredPromoCode")}
    </div>