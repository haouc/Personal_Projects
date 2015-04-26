//
//  SaveNoteView.h
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MyCar.h"
#import <AVFoundation/AVFoundation.h>
#import <AudioToolbox/AudioToolbox.h>

@interface SaveNoteView : UIViewController <AVAudioPlayerDelegate>
- (IBAction)BackToHome:(id)sender;
- (IBAction)moreNotes:(id)sender;
- (IBAction)rememberMyCar:(id)sender;
- (IBAction)goToMyGarage:(id)sender;
- (IBAction)CleanHomeButton:(id)sender;
@property (weak, nonatomic) IBOutlet UITextField *carMake;
@property (weak, nonatomic) IBOutlet UITextField *carModel;
@property (weak, nonatomic) IBOutlet UITextField *carTrim;
@property (weak, nonatomic) IBOutlet UITextField *carYear;
@property (weak, nonatomic) IBOutlet UITextField *carColor;
@property (weak, nonatomic) IBOutlet UITextView *userNotes;
@property (nonatomic) AVAudioPlayer * audio;
@property (weak, nonatomic) NSString *soundPath;

//@property NSInteger clickCounter;

@property NSMutableArray *myCars;
//@property MyCar *addedCar;




@end
