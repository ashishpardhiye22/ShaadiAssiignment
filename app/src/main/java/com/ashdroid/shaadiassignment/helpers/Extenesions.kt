package com.ashdroid.shaadiassignment.helpers

import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.ashdroid.shaadiassignment.R
import com.ashdroid.shaadiassignment.adapters.MatchProfileCardListAdapter
import com.ashdroid.shaadiassignment.db.models.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
fun ImageView.showImage(
    imageUrl: String
) {
    val placeHolder = 0
    try {
        if (imageUrl.isNotBlank()) {
            val defRequestOptions = RequestOptions.placeholderOf(placeHolder)
            Glide.with(context)
                .applyDefaultRequestOptions(defRequestOptions)
                .asBitmap()
                .load(imageUrl)
                .into(this)
        } else {
            setImageResource(placeHolder)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        setImageResource(placeHolder)
    }
}

@BindingAdapter("visible")
fun View.flagVisibility(f: Boolean) {
    isVisible = f
}


fun MatchProfileEntity.toViewData(): MatchProfileCardListAdapter.ViewData {
    return MatchProfileCardListAdapter.ViewData(
        uid = uid,
        fullName = "${matchProfile.name?.title} ${matchProfile.name?.first} ${matchProfile.name?.last}",
        genderAge = "${matchProfile.gender?.first()?.uppercase()}, ${matchProfile.dob?.age} yrs",
        cityStateCountry = "${matchProfile.location?.city}, ${matchProfile.location?.state}, ${matchProfile.location?.country}",
        image = matchProfile.picture?.large ?: "",
        actionPerformed = action == actionAccept || action == actionDecline,
        actionPerformedLabel = if (action == actionAccept) {
            R.string.you_have_accepted_this_profile
        } else {
            R.string.you_have_declined_this_profile
        },
        profile = this
    )
}

fun Fragment.screeHeight(): Int {
    val displayMetrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        requireContext().display?.getRealMetrics(displayMetrics)
    } else {
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
    }
    return displayMetrics.heightPixels
}