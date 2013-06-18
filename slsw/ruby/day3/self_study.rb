
module ActsAsCsv
	
	def self.included(base)
		base.extend ClassMethods
	end
	
	module ClassMethods
		def acts_as_csv
			include InstanceMethods
		end
	end

	module CsvRow
		def method_name
			
		end
	end

	module InstanceMethods
		
		def read
			@csv_contents = []
			filename = self.class.to_s.downcase + '.txt'
			file = File.new(filename)
			@headers = file.gets.chomp.split(',')

			file.each do |row|
				@csv_contents << row.chomp.split(',')
			end
		end

		def each
			@csv_contents
		end
		
		attr_accessor :headers, :csv_contents
		
		def initialize
			read
		end
	end
end

class SelfStudyCsv
	include ActsAsCsv
	acts_as_csv
end

m = SelfStudyCsv.new
puts m.headers.inspect
puts m.csv_contents.inspect
puts "===================="
m.each {|row| puts row}
