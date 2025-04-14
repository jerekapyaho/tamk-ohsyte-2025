with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
with Ada.Containers.Vectors;
with Ada.Calendar;

package Events is

   type Category_Type is
      record
         Primary : Unbounded_String;
         Secondary : Unbounded_String;
      end record;

   type Event_Type is 
      record
         Date : Ada.Calendar.Time;
         Description : Unbounded_String;
         Category : Category_Type;
      end record;

   function "<" (Left, Right : Event_Type) return Boolean;

   --  Instantiate a generic package for a container
   --  that stores Event_Type objects.
   package Event_Vectors is new 
      Ada.Containers.Vectors (Index_Type => Positive, 
                              Element_Type => Event_Type);

   procedure Put (Event : Event_Type);
   procedure Sort (EV : in out Event_Vectors.Vector);

   Test_Events : Event_Vectors.Vector;

private
end Events;
