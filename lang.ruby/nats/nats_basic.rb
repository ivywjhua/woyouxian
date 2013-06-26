require "nats/client"

NATS.start do
	# Simple Subscriber
	puts "wjinhua start"
	NATS.subscribe('foo') {|msg| puts "Msg received : '#{msg}'"}

	# Simple Publisher
	NATS.publish('foo.bar.baz', 'Hello World!')

	# Unsubscribing
	sid = NATS.subscribe('bar') {|msg| puts "Msg received : '#{msg}'"}
	NATS.unsubscribe(sid)

	# Requests
	NATS.request('help') {|response| puts "Got a response: '#{response}'"}

	# Replies
	NATS.subscribe('help') {|msg, reply| NATS.publish(reply, "I'll help!")}

	# Stop using NATS.top, exits EM loop if NATS.start started the loop
	NATS.stop
	puts "wjinhua end"

end


















