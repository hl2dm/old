//package dm.book.rulethirtyfour.ehbrowser;
//
//import cool.dm.ehbrowser.R;
//
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import android.app.ListFragment;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class MessageFragment extends ListFragment {
//	private List<Message> messages;
//	private boolean[] messageExpanded; // ?��??��??��?展�????
//	static String new1;
//	static String new2;
//	static String new3;
//	static String news;
//	static String new5;
//	static String new4;
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		new1=(String) this.getResources().getText(R.string.New1);
//		new2=(String) this.getResources().getText(R.string.New2);
//		new3=(String) this.getResources().getText(R.string.New3);
//		news=(String) this.getResources().getText(R.string.News);
//		new4=(String) this.getResources().getText(R.string.New4);
//		new5=(String) this.getResources().getText(R.string.New5);
//		messages = getMessages();
//		messageExpanded = new boolean[messages.size()];
//		showMessages();
//	}
//	public static List<Message> getMessages() {
//		Date date4 = new GregorianCalendar(2012, 11, 27).getTime();
//		Message message4 = new Message("0004", news, new1, date4);
//		Date date3 = new GregorianCalendar(2012, 12, 3).getTime();
//		Message message3 = new Message("0004", news, new2, date3);
//		Date date2 = new GregorianCalendar(2013, 01, 14).getTime();
//		Message message2 = new Message("0004", news, new3, date2);
//		Date date1 = new GregorianCalendar(2013, 02, 21).getTime();
//		Message message1 = new Message("0004", news, new4, date1);
//		Date date5 = new GregorianCalendar(2013, 04, 05).getTime();
//		Message message5 = new Message("0004", news, new5, date5);
//		ArrayList<Message> messages = new ArrayList<Message>();
//		messages.add(message5);
//		messages.add(message4);
//		messages.add(message3);
//		messages.add(message2);
//		messages.add(message1);
//		return messages;
//	}
//	public void showMessages() {
//		// 使用?��?adapter
//		setListAdapter(new MessageListAdapter(getActivity()));
//		ListView listView = getListView();
//		listView.setTextFilterEnabled(true);
//		// �?��?�能?��?�???��?
//		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//	}
//
//	// 點�?訊息標�??��??�detail
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		((MessageListAdapter) getListAdapter()).expand(position);
//		getListView().setItemChecked(position, true);
//	}
//
//	private class MessageListAdapter extends BaseAdapter {
//		private LayoutInflater layoutInflater;
//
//		public MessageListAdapter(Context context) {
//			layoutInflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		}
//
//		public int getCount() {
//			return messages.size();
//		}
//
//		public Object getItem(int position) {
//			return position;
//		}
//
//		public long getItemId(int position) {
//			return position;
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if (convertView == null) {
//				convertView = layoutInflater.inflate(
//						R.layout.message_listview_item, null);
//			}
//			TextView messageTitle = (TextView) convertView
//					.findViewById(R.id.tv_messageTitle);
//			TextView messageDetail = (TextView) convertView
//					.findViewById(R.id.tv_messageDetail);
//			Message message = messages.get(position);
//			DateFormat df = DateFormat.getDateInstance();
//			String dateStr = df.format(message.getDate());
//			messageTitle.setText(dateStr + " " + message.getTitle());
//			messageDetail.setText(message.getDetail());
//			messageDetail
//					.setVisibility(messageExpanded[position] ? View.VISIBLE
//							: View.GONE);
//			return convertView;
//		}
//
//		public void expand(int position) {
//			messageExpanded[position] = !messageExpanded[position];
//			notifyDataSetChanged();
//		}
//	}
//}