package id.co.gits.gitsdriver.utils

interface GitsBindableAdapter<T> {
    fun setRecyclerViewData(data: ArrayList<T>)
}