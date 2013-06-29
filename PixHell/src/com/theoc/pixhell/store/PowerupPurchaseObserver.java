package com.theoc.pixhell.store;

import java.util.Map;

import android.os.AsyncTask;
import android.util.Log;

import com.amazon.inapp.purchasing.BasePurchasingObserver;
import com.amazon.inapp.purchasing.GetUserIdResponse;
import com.amazon.inapp.purchasing.GetUserIdResponse.GetUserIdRequestStatus;
import com.amazon.inapp.purchasing.Item;
import com.amazon.inapp.purchasing.ItemDataResponse;
import com.amazon.inapp.purchasing.PurchaseResponse;
import com.amazon.inapp.purchasing.PurchasingManager;
import com.amazon.inapp.purchasing.Receipt;
import com.theoc.pixhell.StoreActivity;

public class PowerupPurchaseObserver extends BasePurchasingObserver {

	public final StoreActivity mStoreActivity;
	private static final String TAG = "PowerupPurchaseObserver";
	private String currentUser;

	public PowerupPurchaseObserver(StoreActivity storeActivity) {
		super(storeActivity);
		this.mStoreActivity = storeActivity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onGetUserIdResponse(GetUserIdResponse getUserIdResponse) {
		Log.v(TAG, "onGetUserIdResponse recieved: Response -"
				+ getUserIdResponse);
		Log.v(TAG, "RequestId:" + getUserIdResponse.getRequestId());
		Log.v(TAG,
				"IdRequestStatus:" + getUserIdResponse.getUserIdRequestStatus());
		new GetUserIdAsyncTask().execute(getUserIdResponse);
	}

	public void onItemDataResponse(final ItemDataResponse itemDataResponse) {

		Log.v(TAG, "onItemDataResponse recieved");

		Log.v(TAG,
				"ItemDataRequestStatus"
						+ itemDataResponse.getItemDataRequestStatus());

		Log.v(TAG, "ItemDataRequestId" + itemDataResponse.getRequestId());

		new ItemDataAsyncTask().execute(itemDataResponse);

	}

	@Override
	public void onPurchaseResponse(final PurchaseResponse purchaseResponse) {

		Log.v(TAG, "onPurchaseResponse recieved");

		Log.v(TAG,
				"PurchaseRequestStatus:"
						+ purchaseResponse.getPurchaseRequestStatus());

		new PurchaseAsyncTask().execute(purchaseResponse);

	}

	@Override
	public void onSdkAvailable(boolean isSandboxMode) {
		Log.w("", "" + isSandboxMode);
		PurchasingManager.initiateGetUserIdRequest();
	}

	private void printReceipt(final Receipt receipt) {

		Log.v(

		TAG,

		String.format("Receipt: ItemType: %s Sku: %s SubscriptionPeriod: %s",
				receipt.getItemType(),

				receipt.getSku(), receipt.getSubscriptionPeriod()));

	}

	private class GetUserIdAsyncTask extends
			AsyncTask<GetUserIdResponse, Void, Boolean> {

		protected Boolean doInBackground(final GetUserIdResponse... params) {

			GetUserIdResponse getUserIdResponse = params[0];

			if (getUserIdResponse.getUserIdRequestStatus() == GetUserIdRequestStatus.SUCCESSFUL) {

				final String userId = getUserIdResponse.getUserId();

				// Each UserID has their own shared preferences file, and we'll
				// load that file when a new user logs in.

				mStoreActivity.setCurrentUser(userId);

				return true;

			} else {

				Log.v(TAG, "onGetUserIdResponse: Unable to get user ID.");

				return false;

			}

		}

	}

	private class ItemDataAsyncTask extends
			AsyncTask<ItemDataResponse, Void, Void> {

		@Override
		protected Void doInBackground(final ItemDataResponse... params) {

			final ItemDataResponse itemDataResponse = params[0];

			switch (itemDataResponse.getItemDataRequestStatus()) {

			case SUCCESSFUL_WITH_UNAVAILABLE_SKUS:

				// Skus that you cannot purchase will be here.

				for (final String s : itemDataResponse.getUnavailableSkus()) {

					Log.v(TAG, "Unavailable SKU:" + s);

				}

			case SUCCESSFUL:

				// Information you'll want to display about your IAP items is
				// here

				// In this example we'll simply log them.

				final Map<String, Item> items = itemDataResponse.getItemData();

				for (final String key : items.keySet()) {

					Item i = items.get(key);
					// TODO Show a dialog that the IAP Purchase is successful

					Log.v(TAG,
							String.format(
									"Item: %s\n Type: %s\n SKU: %s\n Price: %s\n Description: %s\n",
									i.getTitle(), i.getItemType(), i.getSku(),
									i.getPrice(), i.getDescription()));

				}

				break;

			case FAILED:

				// On failed responses will fail gracefully.

				break;

			}

			return null;

		}

	}

	private class PurchaseAsyncTask extends
			AsyncTask<PurchaseResponse, Void, Boolean> {

		@Override
		protected Boolean doInBackground(final PurchaseResponse... params) {

			final PurchaseResponse purchaseResponse = params[0];

			final String userId = mStoreActivity.getCurrentUser();

			switch (purchaseResponse.getPurchaseRequestStatus()) {

			case SUCCESSFUL:

				/**
				 * 
				 * You can verify the receipt and fulfill the purchase on
				 * successful responses.
				 */

				final Receipt receipt = purchaseResponse.getReceipt();

				String key = "";

				switch (receipt.getItemType()) {

				case CONSUMABLE:

					// TODO store the count of power up based on SKU string
					/*
					 * int numClicks =
					 * settings.getInt(ButtonClickerActivity.NUM_CLICKS, 0);
					 * 
					 * editor.putInt(ButtonClickerActivity.NUM_CLICKS, numClicks
					 * + 10);
					 */
					mStoreActivity.update(receipt.getSku());
					break;
				}

				printReceipt(purchaseResponse.getReceipt());

				return true;

			case FAILED:

				/**
				 * 
				 * If the purchase failed for some reason, (The customer
				 * canceled the order, or some other
				 * 
				 * extraneous circumstance happens) the application ignores the
				 * request and logs the failure.
				 */

				/*Log.v(TAG,
						"Failed purchase for request"
								+ mStoreActivity.requestIdPowerupMap
										.get(purchaseResponse.getRequestId()));
*/
				return false;

			case INVALID_SKU:

				/**
				 * 
				 * If the sku that was purchased was invalid, the application
				 * ignores the request and logs the failure.
				 * 
				 * This can happen when there is a sku mismatch between what is
				 * sent from the application and what
				 * 
				 * currently exists on the dev portal.
				 */

				Log.v(TAG,
						"Invalid Sku for request "
								+ mStoreActivity.requestIdPowerupMap
										.get(purchaseResponse.getRequestId()));

				return false;

			}

			return false;

		}

		@Override
		protected void onPostExecute(final Boolean success) {

			super.onPostExecute(success);

			if (success) {

				//mStoreActivity.update();

			}

		}
	}

}
