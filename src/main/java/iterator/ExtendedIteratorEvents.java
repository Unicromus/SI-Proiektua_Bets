package iterator;

import java.util.List;

import domain.Event;

public class ExtendedIteratorEvents<Event> implements ExtendedIterator<Object> {

	List<Event> events;
	int position = 0;

	public ExtendedIteratorEvents(List<Event> e) {
		this.events = e;
	}

	@Override
	public boolean hasNext() {
		return position < events.size();
	}

	@Override
	public Object next() {
		Event event = events.get(position);
		position++;
		return event;
	}

	@Override
	public Object previous() {
		Event event = events.get(position);
		position--;
		return event;
	}

	@Override
	public boolean hasPrevious() {
		return position >= 0;
	}

	@Override
	public void goFirst() {
		position = 0;
	}

	@Override
	public void goLast() {
		position = events.size() - 1;
	}

}
