package zhoujian.flexbox.adapter;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import zhoujian.flexbox.widget.BaseTagView;
import zhoujian.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhoujian.flexbox.widget.TagFlowLayout;
import zhoujian.flexbox.interfaces.TagWithListener;


public abstract class TagAdapter<V extends BaseTagView<T>, T> {

    private Context context;

    private TagFlowLayout rootView;

    private List<T> source;


    private List<T> selectItems;

    private Map<V, T> viewMap;

    private OnFlexboxSubscribeListener<T> onSubscribeListener;

    private boolean isShowHighlight = true;

    private int maxSelection;

    protected int itemDefaultDrawable;
    protected int itemSelectDrawable;

    protected int itemDefaultTextColor;
    protected int itemSelectTextColor;

    private int mode;

    public void setOnSubscribeListener(OnFlexboxSubscribeListener<T> onSubscribeListener) {
        this.onSubscribeListener = onSubscribeListener;
    }

    public void setSource(List<T> source) {
        this.source = source;
    }

    public void setSelectItems(List<T> selectItems) {
        this.selectItems = selectItems;
    }

    public List<T> getSelectItems() {
        return selectItems;
    }

    public TagAdapter(Context context, List<T> source) {
        this.context = context;
        this.source = source;
        viewMap = new ArrayMap<>();
    }

    public TagAdapter(Context context, List<T> source, List<T> selectItems) {
        this.context = context;
        this.source = source;
        this.selectItems = selectItems;
        viewMap = new ArrayMap<>();
    }

    public Context getContext() {
        return context;
    }

    public List<T> getData() {
        return source;
    }


    public void bindView(TagFlowLayout rootView) {
        if (rootView == null) {
            throw new NullPointerException("未初始化TagFlowLayout");
        }
        this.rootView = rootView;
        isShowHighlight = rootView.isShowHighlight();
        itemDefaultDrawable = rootView.getItemDefaultDrawable();
        itemSelectDrawable = rootView.getItemSelectDrawable();
        itemDefaultTextColor = rootView.getItemDefaultTextColor();
        itemSelectTextColor = rootView.getItemSelectTextColor();
        maxSelection = rootView.getMaxSelection();
        mode = rootView.getMode();
    }


    public void addTags() {
        if (source == null || source.size() <= 0) return;
        rootView.removeAllViews();
        for (T item : source) {
            if (item == null) continue;
            final BaseTagView<T> view = addTag(item);
            initSelectedViews((V) view);
            view.setListener(new TagWithListener<T>() {
                @Override
                public void onItemSelect(T item) {
                    if (mode == TagFlowLayout.MODE_SINGLE_SELECT) {
                        if (isShowHighlight) view.selectItemChangeColorState();
                        singleSelectMode(item);
                    } else {
                        List<T> selectList = getSelectedList();
                        if ((maxSelection <= selectList.size() && maxSelection > 0) && !view.isItemSelected()) {
                            Toast.makeText(getContext(), "最多选择" + maxSelection + "个标签", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isShowHighlight) view.selectItemChangeColorState();
                    }
                    if (onSubscribeListener != null) {
                        onSubscribeListener.onSubscribe(getSelectedList());
                    }
                }
            });
            viewMap.put((V) view, item);
            rootView.addView(view);
        }
    }


    private void initSelectedViews(V view) {
        if (!isShowHighlight) return;
        if (selectItems == null || selectItems.size() <= 0) return;
        for (T select : selectItems) {
            if (checkIsItemNull(select)) continue;
            if (checkIsItemSame(view, select)) {
                view.setItemSelected(true);
                break;
            }
        }
    }


    private void singleSelectMode(T item) {
        if (!isShowHighlight) return;
        for (BaseTagView<T> view : viewMap.keySet()) {
            if (checkIsItemSame((V) view, item)) {
                view.setItemSelected(true);
            } else {
                view.setItemSelected(false);
            }
        }
    }



    public void notifyDataSetChanged() {
        addTags();
    }


    protected abstract boolean checkIsItemSame(V view, T item);


    protected abstract boolean checkIsItemNull(T item);


    protected abstract BaseTagView<T> addTag(T item);


    protected int getCount() {
        if (source == null) return 0;
        return source.size();
    }


    @SuppressWarnings("SuspiciousMethodCalls")
    public List<T> getSelectedList() {
        List<T> selectedList = new ArrayList<>();
        for (BaseTagView<T> view : viewMap.keySet()) {
            if (view.isItemSelected()) {
                T item = viewMap.get(view);
                selectedList.add(item);
            }
        }
        return selectedList;
    }
}
